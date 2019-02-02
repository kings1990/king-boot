package com.kingboot.basic.controller;


import com.kingboot.basic.task.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping ("/task")
@Controller
public class TaskTestController {
    @Autowired
    private AsyncTask asyncTask;
    @GetMapping (value = "/1",name = "异步任务测试")
    @ResponseBody
    public String testTask() throws InterruptedException {
        long begin = System.currentTimeMillis();
        asyncTask.task1();
        asyncTask.task2();
        asyncTask.task3();
        long end = System.currentTimeMillis();
        System.out.println("总耗时:"+(end-begin));
        return "1";
    }
}

