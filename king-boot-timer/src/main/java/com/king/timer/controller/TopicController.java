package com.king.timer.controller;


import com.king.timer.entity.TimerEntity;
import com.king.timer.entity.TimerTopic;
import com.king.timer.mongo.MongoDBService;
import com.king.timer.mongo.MongoPage;
import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping ("topic")
public class TopicController {
    
    private static final String TIMER = "Timer";
    private static final String TIMER_TOPIC = "TimerTopic";
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private MongoDBService mongoDBService;
    @Autowired
    private MongoTemplate mongoTemplate;
    
    
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
    public String topicList() {
        return "topic/topicList";
    }
    
    
    /**
     * <p class="detail">
     * 功能：topic列表会异步访问这个方法来获取需要的数据
     * </p>
     * @param topicName :
     * @param limit     每页展示数据的数量
     * @param offset    开始查询的偏移值
     *
     * @return
     * @throws Exception the exception
     * @author Kings
     * @date 2017.02.23 14:24:09
     */
    @ResponseBody
    @RequestMapping (value = "loadTopicList", method = RequestMethod.GET)
    public Map<String, Object> loadTopicList(String topicName, Integer limit, Integer offset, String createTimeBeg, String createTimeEnd) {
        //转换到当前查询页码
        Integer page = (offset / limit) + 1;
        //转换当前页面查询容量pagesize
        Integer rows = limit;
        Query query = new Query();
        Criteria criteria = new Criteria();
        //根据topic降序排序
        query.with(new Sort(Sort.Direction.ASC, "topic"));
        
        //↓↓↓↓↓↓↓↓↓↓设置查询参数↓↓↓↓↓↓↓↓↓↓
        //查询未删除的定时器
        query.addCriteria(Criteria.where("delStatus").is(false));
        if (StringUtils.isNotBlank(topicName)) {
            criteria.and("topic").regex(".*" + topicName + ".*");
        }
        if ((createTimeBeg != null && ! createTimeBeg.equals("")) && (createTimeEnd == null || createTimeEnd.equals(""))) {
            criteria.and("createTime").gt(createTimeBeg + " 0:0:0");
        }
        if ((createTimeEnd != null && ! createTimeEnd.equals("")) && (createTimeBeg == null || createTimeBeg.equals(""))) {
            criteria.and("createTime").lte(createTimeEnd + " 23:59:59");
        }
        if (createTimeBeg != null && ! createTimeBeg.equals("") && createTimeEnd != null && ! createTimeEnd.equals("")) {
            criteria.and("createTime").gt(createTimeBeg + " 0:0:0").lte(createTimeEnd + " 23:59:59");
        }
        //↑↑↑↑↑↑↑↑↑↑设置查询参数↑↑↑↑↑↑↑↑↑↑
        
        query.addCriteria(criteria);
        MongoPage mongoPage = new MongoPage();
        mongoPage.setPageNum(page);
        mongoPage.setPageSize(rows);
        mongoPage = mongoDBService.findPage(query, mongoPage, Object.class, TIMER_TOPIC);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", mongoPage.getList());
        jsonMap.put("total", mongoPage.getTotal());
        return jsonMap;
    }
    
    /**
     * <p class="detail">
     * 功能:新增topic到mongodb
     * </p>
     * @param topic :
     *
     * @return string
     * @author Kings
     * @date 2018.04.19 09:38:07
     */
    @RequestMapping (value = "addTopic", method = RequestMethod.POST)
    @ResponseBody
    public String addTopic(String topic) {
        Query query = new Query(Criteria.where("topic").is(topic));
        if (mongoTemplate.exists(query, TIMER_TOPIC)) {
            return "fail";
        }
        TimerTopic newTopic = new TimerTopic();
        newTopic.setDelStatus(false);
        newTopic.setTopic(topic);
        newTopic.setCreateTime(sdf.format(new Date()));
        mongoTemplate.insert(newTopic, TIMER_TOPIC);
        return "ok";
    }
    
    
    /**
     * <p class="detail">
     * 功能:删除topic
     * </p>
     * @param id :
     *
     * @return string
     * @author Kings
     * @date 2018.04.19 09:38:07
     */
    @RequestMapping (value = "{id}/delTopic", method = RequestMethod.GET)
    @ResponseBody
    public String delTopic(@PathVariable String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        TimerTopic timerTopic = mongoTemplate.findOne(query, TimerTopic.class, TIMER_TOPIC);
        
        Query timerQuery = new Query(Criteria.where("topic").is(timerTopic.getTopic()));
        timerQuery.addCriteria(Criteria.where("delStatus").is(false));
        //如果存在timer使用该topic  不允许删除
        if (mongoTemplate.exists(timerQuery, TimerEntity.class, TIMER)) {
            return "fail";
        }
        timerTopic.setDelStatus(true);
        mongoTemplate.save(timerTopic, TIMER_TOPIC);
        return "ok";
    }
    
    
}
