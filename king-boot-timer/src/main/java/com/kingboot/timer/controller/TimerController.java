package com.kingboot.timer.controller;

import com.kingboot.timer.config.Producer;
import com.kingboot.timer.entity.Executor;
import com.kingboot.timer.entity.TimerEntity;
import com.kingboot.timer.entity.TimerTopic;
import com.kingboot.timer.entity.TopicInfo;
import com.kingboot.timer.job.KafkaJob;
import com.kingboot.timer.mongo.MongoDbService;
import com.kingboot.timer.mongo.MongoPage;
import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import java.util.stream.Collectors;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


/**
 * <p class="detail">
 * 功能:
 * </p>
 * @author Kings
 * @ClassName Timer controller.
 * @Version V1.0.
 * @date 2018.04.19 09:38:06
 */
@Controller
@RequestMapping ("timer")
public class TimerController {
	
	private static final String TIMER = "Timer";
	private static final String TIMER_TOPIC = "TimerTopic";
	private static final Logger logger = LoggerFactory.getLogger(TimerController.class);
	private static final String TOPICINFO = "TopicInfo";
	
	@Resource
	private Scheduler scheduler;
	@Autowired
	private MongoDbService mongoDbService;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Value ("${spring.kafka.bootstrap-servers}")
	private String servers;
	@Autowired
	private Producer producer;
	
	
	/**
	 * <p class="detail">
	 * 功能:定时器列表
	 * </p>
	 * @return string
	 * @throws SchedulerException the scheduler exception
	 * @author Kings
	 * @date 2018.04.19 09:38:06
	 */
	@RequestMapping (value = "list", method = RequestMethod.GET)
	public String toList() {
		return "timer/timerList";
	}
	
	/**
	 * <p class="detail">
	 * 功能:启动定时器
	 * </p>
	 * @param id :
	 *
	 * @return string
	 * @throws SchedulerException the scheduler exception
	 * @author Kings
	 * @date 2018.04.19 09:38:06
	 */
	@RequestMapping (value = "{id}/startTimer", method = RequestMethod.POST)
	@ResponseBody
	public String startTimer(@PathVariable String id) {
		
		Query query = new Query(Criteria.where("_id").is(id));
		
		TimerEntity timerEntity = mongoTemplate.findOne(query, TimerEntity.class, TIMER);
		timerEntity.setStatus(true);
		
		//检查message格式
		if (! checkMessage(timerEntity.getMessage())) {
			return "mesError";
		}
		//检查cron格式
		if (! checkCron(timerEntity.getCorn())) {
			return "cronError";
		}
		
		//quartz启动执行器
		try {
			doStartTimer(timerEntity);
		} catch (SchedulerException e) {
			logger.error("timer {} start fail", timerEntity.getName());
			return "fail";
		}
		mongoTemplate.save(timerEntity, TIMER);
		return "ok";
	}
	
	/**
	 * quartz启动执行器
	 * @param timerEntity
	 * @throws SchedulerException
	 */
	private void doStartTimer(TimerEntity timerEntity) throws SchedulerException {
		Map<String, Object> map = new HashMap<>(2);
		map.put("producer", producer);
		SimpleDateFormat sdf = new SimpleDateFormat(timerEntity.getMessage());
		map.put("sdf", sdf);
		JobDetail job = newJob(KafkaJob.class).usingJobData(new JobDataMap(map)).usingJobData("topic", timerEntity.getTopic()).withIdentity("Timer_" + timerEntity.getTopic()).build();
		
		Trigger trigger = newTrigger().withIdentity("trigger_" + timerEntity.getTopic()).startNow().withSchedule(CronScheduleBuilder.cronSchedule(timerEntity.getCorn())).build();
		scheduler.scheduleJob(job, trigger);
	}
	
	/**
	 * <p class="detail">
	 * 功能:停止定时器
	 * </p>
	 * @param id :
	 *
	 * @return string
	 * @throws SchedulerException the scheduler exception
	 * @author Kings
	 * @date 2018.04.19 09:38:06
	 */
	@RequestMapping (value = "{id}/stopTimer", method = RequestMethod.POST)
	@ResponseBody
	public String stopTimer(@PathVariable String id) {
		
		Query query = new Query(Criteria.where("_id").is(id));
		TimerEntity timerEntity = mongoTemplate.findOne(query, TimerEntity.class, TIMER);
		timerEntity.setStatus(false);
		try {
			doStopTimer(timerEntity);
		} catch (SchedulerException e) {
			logger.error("timer {} stop fail", timerEntity.getName());
			return "fail";
		}
		mongoTemplate.save(timerEntity, TIMER);
		return "ok";
	}
	
	/**
	 * quartz停止执行器
	 * @param timerEntity
	 * @throws SchedulerException
	 */
	private void doStopTimer(TimerEntity timerEntity) throws SchedulerException {
		JobDetail job = newJob(KafkaJob.class).withIdentity("Timer_" + timerEntity.getTopic()).build();
		scheduler.deleteJob(job.getKey());
	}
	
	/**
	 * <p class="detail">
	 * 功能：定时器列表会异步访问这个方法来获取需要的数据
	 * </p>
	 * @param timerName :定时器名称
	 * @param topic     :
	 * @param describe  :
	 * @param limit     每页展示数据的数量
	 * @param offset    开始查询的偏移值
	 *
	 * @return
	 * @throws Exception the exception
	 * @author Kings
	 * @date 2017.02.23 14:24:09
	 */
	@ResponseBody
	@RequestMapping (value = "loadTimerList", method = RequestMethod.GET)
	public Map<String, Object> loadTimerList(String timerName, String topic, String describe, Integer limit, Integer offset, String createTimeBeg, String createTimeEnd) {
		//转换到当前查询页码
		Integer page = (offset / limit) + 1;
		//转换当前页面查询容量pagesize
		Integer rows = limit;
		Query query = new Query();
		Criteria criteria = new Criteria();
		//根据createTime降序排序
		query.with(new Sort(Sort.Direction.DESC, "_id"));
		
		//↓↓↓↓↓↓↓↓↓↓设置查询参数↓↓↓↓↓↓↓↓↓↓
		//查询未删除的定时器
		query.addCriteria(Criteria.where("delStatus").is(false));
		
		if (StringUtils.isNotBlank(timerName)) {
			criteria.and("name").regex(".*" + timerName + ".*");
		}
		if (StringUtils.isNotBlank(topic)) {
			criteria.and("topic").regex(".*" + topic + ".*");
		}
		if (StringUtils.isNotBlank(describe)) {
			criteria.and("describe").regex(".*" + describe + ".*");
		}
		
		if (StringUtils.isNotBlank(createTimeBeg) && StringUtils.isNotBlank(createTimeEnd)) {
			criteria.and("createTime").gt(createTimeBeg + " 0:0:0");
		}
		
		if (StringUtils.isNotBlank(createTimeEnd) && StringUtils.isBlank(createTimeBeg)) {
			criteria.and("createTime").lte(createTimeEnd + " 23:59:59");
		}
		
		if(StringUtils.isNotBlank(createTimeBeg) && StringUtils.isNotBlank(createTimeEnd)){
			criteria.and("createTime").gt(createTimeBeg + " 0:0:0").lte(createTimeEnd + " 23:59:59");
		}
		
		//↑↑↑↑↑↑↑↑↑↑设置查询参数↑↑↑↑↑↑↑↑↑↑
		
		query.addCriteria(criteria);
		MongoPage mongoPage = new MongoPage();
		mongoPage.setPageNum(page);
		mongoPage.setPageSize(rows);
		mongoPage = mongoDbService.findPage(query, mongoPage, TimerEntity.class, TIMER);
		Map<String, Object> jsonMap = new HashMap<>(2);
		jsonMap.put("rows", mongoPage.getList());
		jsonMap.put("total", mongoPage.getTotal());
		return jsonMap;
	}
	
	
	/**
	 * <p class="detail">
	 * 功能:跳转到修改定时器页面
	 * </p>
	 * @param model :
	 * @param id    :
	 *
	 * @return string
	 * @author Kings
	 * @date 2018.04.19 09:38:07
	 */
	@RequestMapping (value = "{id}/editTimer")
	public String editTimer(Model model, @PathVariable String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		TimerEntity timerEntity = mongoTemplate.findOne(query, TimerEntity.class, TIMER);
		model.addAttribute("timer", timerEntity);
		model.addAttribute("topics", getAllTopics());
		return "timer/timerEdit";
	}
	
	/**
	 * <p class="detail">
	 * 功能:修改定时器
	 * </p>
	 * @param timerEntity :
	 * @param id          :
	 *
	 * @return string
	 * @throws SchedulerException the scheduler exception
	 * @author Kings
	 * @date 2018.04.19 09:38:07
	 */
	@RequestMapping (value = "{id}/doEditTimer", method = RequestMethod.POST)
	public String doEditTimer(TimerEntity timerEntity, @PathVariable String id) {
		
		//检查message格式
		if (! checkMessage(timerEntity.getMessage())) {
			return "mesError";
		}
		//检查cron格式
		if (! checkCron(timerEntity.getCorn())) {
			return "cronError";
		}
		
		Query query = new Query(Criteria.where("_id").is(id));
		TimerEntity oldTimerEntity = mongoTemplate.findOne(query, TimerEntity.class, TIMER);
		
		try {
			//如果老的topic启用中则停用
			if (oldTimerEntity.getStatus()) {
				doStopTimer(oldTimerEntity);
			}
			//如果新的topic启用则开启
			if (timerEntity.getStatus()) {
				doStartTimer(timerEntity);
			}
		} catch (SchedulerException e) {
			logger.error("timer {} update fail", timerEntity.getName());
			return "fail";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timerEntity.set_id(id);
		timerEntity.setCreateTime(sdf.format(new Date()));
		mongoTemplate.save(timerEntity, TIMER);
		return "redirect:/timer/list";
	}
	
	/**
	 * <p class="detail">
	 * 功能:跳转到新增定时器页面
	 * </p>
	 * @param model :
	 *
	 * @return string
	 * @author Kings
	 * @date 2018.04.19 09:38:07
	 */
	@RequestMapping (value = "/addTimer")
	public String addTimer(Model model) {
		model.addAttribute("topics", getAllTopics());
		return "timer/timerAdd";
	}
	
	
	/**
	 * <p class="detail">
	 * 功能:新增定时器
	 * </p>
	 * @param timerEntity :
	 *
	 * @return string
	 * @throws SchedulerException the scheduler exception
	 * @author Kings
	 * @date 2018.04.19 09:38:07
	 */
	@RequestMapping (value = "doAddTimer")
	public String doAddTimer(TimerEntity timerEntity) {
		//检查message格式
		if (! checkMessage(timerEntity.getMessage())) {
			return "mesError";
		}
		//检查cron格式
		if (! checkCron(timerEntity.getCorn())) {
			return "cronError";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timerEntity.setTopic(timerEntity.getTopic());
		timerEntity.setCreateTime(sdf.format(new Date()));
		mongoTemplate.insert(timerEntity, TIMER);
		
		if (timerEntity.getStatus()) {
			//quartz启动执行器
			try {
				doStartTimer(timerEntity);
			} catch (SchedulerException e) {
				logger.error("timer {} start fail", timerEntity.getName());
				return "fail";
			}
		}
		return "redirect:/timer/list";
	}
	
	
	/**
	 * <p class="detail">
	 * 功能:删除定时器
	 * </p>
	 * @param id :
	 *
	 * @return string
	 * @author Kings
	 * @date 2018.04.19 09:38:07
	 */
	@RequestMapping (value = "{id}/delTimer", method = RequestMethod.GET)
	@ResponseBody
	public String delTimer(@PathVariable String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		TimerEntity timerEntity = mongoTemplate.findOne(query, TimerEntity.class, TIMER);
		//未停用的定时器不能删除
		if (timerEntity.getStatus()) {
			return "fail";
		} else {
			timerEntity.setDelStatus(true);
			mongoTemplate.save(timerEntity, TIMER);
			return "ok";
		}
	}
	
	/**
	 * <p class="detail">
	 * 功能:执行定时器
	 * </p>
	 * @param id :
	 *
	 * @return string
	 * @author Kings
	 * @date 2018.04.19 09:38:07
	 */
	@RequestMapping (value = "{id}/executeTimer", method = RequestMethod.GET)
	@ResponseBody
	public String executeTimer(@PathVariable String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		TimerEntity timerEntity = mongoTemplate.findOne(query, TimerEntity.class, TIMER);
		//检查message格式
		if (! checkMessage(timerEntity.getMessage())) {
			return "mesError";
		}
		//检查cron格式
		if (! checkCron(timerEntity.getCorn())) {
			return "cronError";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(timerEntity.getMessage());
			String message = sdf.format(new Date());
			producer.send(timerEntity.getTopic(), new GenericMessage<>(message));
		} catch (RuntimeException e) {
			logger.error("fail to execute Timer {}", timerEntity.getName());
			return "fail";
		}
		return "ok";
	}
	
	
	/**
	 * <p class="detail">
	 * 功能:跳转到定时器详情页面
	 * </p>
	 * @param model :
	 * @param id    :
	 *
	 * @return string
	 * @throws ParseException the parse exception
	 * @author Kings
	 * @date 2018.04.19 09:38:07
	 */
	@RequestMapping (value = "{id}/detailTimer")
	public String detailTimer(Model model, @PathVariable String id) throws Exception {
		Query query = new Query(Criteria.where("_id").is(id));
		TimerEntity timerEntity = mongoTemplate.findOne(query, TimerEntity.class, TIMER);
		model.addAttribute("timer", timerEntity);
		Executor executor = new Executor();
		
		//上次应该执行时间
		try {
			String lastExpectTime = getLastExpectTime(timerEntity.getCorn());
			executor.setLastExpectTime(lastExpectTime);
		} catch (ParseException e) {
			logger.error("cron {} is wrong ", timerEntity.getCorn());
			executor.setLastExpectTime("corn " + timerEntity.getCorn() + " is wrong");
		} catch (IndexOutOfBoundsException e) {
			logger.error("cron {} lastTime is not exist ", timerEntity.getCorn());
			executor.setLastExpectTime("corn " + timerEntity.getCorn() + " lastTime is not exist");
		}
		//获取所有groupId 及上次执行时间
		List<TopicInfo> topicInfos = getAllGroupsForTopic(timerEntity.getTopic());
		executor.setTopicInfos(topicInfos);
		model.addAttribute("executor", executor);
		return "timer/timerDetail";
	}
	
	
	/**
	 * 获取所有TimerTopic
	 * @return
	 */
	private List<TimerTopic> getAllTopics() {
		Query query = new Query(Criteria.where("delStatus").is(false));
		//根据topic降序排序
		query.with(new Sort(Sort.Direction.ASC, "topic"));
		List<TimerTopic> timerTopics = mongoTemplate.find(query, TimerTopic.class, TIMER_TOPIC);
		return timerTopics;
	}
	
	
	/**
	 * 根据corn获取理论上次执行时间
	 * @param corn
	 * @return
	 * @throws ParseException
	 */
	private String getLastExpectTime(String corn) throws ParseException {
		CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
		cronTriggerImpl.setCronExpression(corn);
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		//把统计的区间段设置为从一个月前到现在
		calendar.add(Calendar.MONTH, - 1);
		List<Date> dates = TriggerUtils.computeFireTimesBetween(cronTriggerImpl, null, calendar.getTime(), now);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dates.get(dates.size() - 1));
	}
	
	
	private List<TopicInfo> getAllGroupsForTopic(String topic) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("topic").is(topic)).with(new Sort(Sort.Direction.ASC, "lastTime"));
		List<TopicInfo> topicInfoList = mongoTemplate.find(query, TopicInfo.class, TOPICINFO);
		
		List<TopicInfo> result = new ArrayList<>();
		Map<String, List<TopicInfo>> listMap = topicInfoList.stream().sorted(Comparator.comparing(TopicInfo :: getLastTime).reversed()).collect(Collectors.groupingBy(TopicInfo :: getConsumer));
		for (Map.Entry<String, List<TopicInfo>> entry : listMap.entrySet()) {
			result.add(entry.getValue().get(0));
		}
		
		return result;
	}
	
	/**
	 * 判断message是否符合要求
	 * @param message
	 * @return
	 */
	private boolean checkMessage(String message) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(message);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断cron是否符合要求
	 * @param cron
	 * @return
	 */
	private boolean checkCron(String cron) {
		CronTriggerImpl trigger = new CronTriggerImpl();
		try {
			trigger.setCronExpression(cron);
			Date date = trigger.computeFirstFireTime(null);
			return date != null;
		} catch (Exception e) {
			return false;
		}
	}
	
}
