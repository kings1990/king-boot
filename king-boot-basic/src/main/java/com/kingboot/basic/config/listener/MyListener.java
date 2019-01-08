package com.kingboot.basic.config.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class MyListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug(MyListener.class.getCanonicalName()+" init...");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.debug(MyListener.class.getCanonicalName()+" destroyed...");
    }
}
