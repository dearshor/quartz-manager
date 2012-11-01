package com.kjlink.quartz.manager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void testMethod(String triggerName, String group) {
		// TODO Auto-generated method stub
		logger.debug("正在执行：{}/{}", group, triggerName);
	}

	public void testMethod2(String triggerName, String group) {
		// TODO Auto-generated method stub
		logger.debug("正在执行：{}/{}", group, triggerName);
	}

}
