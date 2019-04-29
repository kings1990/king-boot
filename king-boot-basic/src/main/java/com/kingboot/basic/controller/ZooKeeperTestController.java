package com.kingboot.basic.controller;


import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping ("/zoo")
@Controller
public class ZooKeeperTestController {
	
	
	@Autowired
	private ZooKeeper zooKeeper;
	
	@Autowired
	private ZkClient zkClient;
	
	@GetMapping (name = "zookeeper调用", value = "/1")
	@ResponseBody
	public List<String> test1() throws KeeperException, InterruptedException {
		List<String> serviceNameList = zooKeeper.getChildren("/dubbo", true);
		for (String serviceName : serviceNameList) {
			Stat stat = new Stat();
			zkClient.readData("/dubbo/" + serviceName, stat);
			InfoDubboService infoDubboService = new InfoDubboService(serviceName, stat);
			List<String> keyList = zkClient.getChildren("/dubbo/" + serviceName);
			System.out.println("[");
			for (String key : keyList) {
				List<String> children3 = zkClient.getChildren("/dubbo/" + serviceName + "/" + key);
				System.out.println("\t{\"key\":" + key + ",\"value\":" + children3);
			}
			System.out.println("]");
		}
		return serviceNameList;
	}
	
	class InfoDubboService {
		private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		private String consumer;
		private Stat stat;
		private String lastTime;
		
		
		public InfoDubboService(String consumer, Stat stat) {
			this.consumer = consumer;
			this.stat = stat;
			Date date = new Date();
			date.setTime(stat.getMtime());
			this.lastTime = dateFormat.format(date);
		}
		
		@Override
		public String toString() {
			return "{\"consumer\":" + consumer + ",\"lastTime" + lastTime + "\"}";
		}
		
		public String getConsumer() {
			return consumer;
		}
		
		public void setConsumer(String consumer) {
			this.consumer = consumer;
		}
		
		public Stat getStat() {
			return stat;
		}
		
		public void setStat(Stat stat) {
			this.stat = stat;
		}
		
		public String getLastTime() {
			return lastTime;
		}
		
		public void setLastTime(String lastTime) {
			this.lastTime = lastTime;
		}
	}
}

