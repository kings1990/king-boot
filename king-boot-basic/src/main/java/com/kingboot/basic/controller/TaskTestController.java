package com.kingboot.basic.controller;


import com.kingboot.basic.task.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Future;

@RequestMapping ("/task")
@Controller
public class TaskTestController {
	@Autowired
	private AsyncTask asyncTask;
	
	@GetMapping (value = "/1", name = "异步任务测试")
	@ResponseBody
	public String testTask() throws InterruptedException {
		long begin = System.currentTimeMillis();
		Future<String> future1 = asyncTask.task1();
		Future<String> future2 = asyncTask.task2();
		Future<String> future3 = asyncTask.task3();
		
		while (true) {
			if (future1.isDone() && future2.isDone() && future3.isDone()) {
				break;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("总耗时:" + (end - begin));
		return "1";
	}
}

