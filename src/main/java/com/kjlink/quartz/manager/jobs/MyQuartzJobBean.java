package com.kjlink.quartz.manager.jobs;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.kjlink.quartz.manager.service.SimpleService;

public class MyQuartzJobBean extends QuartzJobBean {

	private SimpleService simpleService;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void setSimpleService(SimpleService simpleService) {
		this.simpleService = simpleService;
	}

	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
//		Trigger trigger = jobexecutioncontext.getTrigger();
//		String triggerName = trigger.getName();
//		logger.debug("触发器：{}", triggerName);
//		String group = trigger.getGroup();
//		logger.debug("隶属于组：{}", group);
		
		//根据Trigger组别调用不同的业务逻辑方法
//		if (StringUtils.equals(group, Scheduler.DEFAULT_GROUP)) {
//			simpleService.testMethod(triggerName, group);
//		} else {
//			simpleService.testMethod2(triggerName, group);
//		}
	}

}
