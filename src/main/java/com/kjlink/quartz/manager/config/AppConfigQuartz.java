package com.kjlink.quartz.manager.config;

import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.kjlink.quartz.manager.service.SimpleService;

@Configuration
public class AppConfigQuartz {
	
	@Autowired
	private SimpleService simpleService;
	
	@Autowired
	private DataSource dataSource;

	public @Bean JobDetail jobDetail() {
		MethodInvokingJobDetailFactoryBean jobDetailBean = new MethodInvokingJobDetailFactoryBean();
		jobDetailBean.setTargetObject(simpleService);
		jobDetailBean.setTargetMethod("testMethod");
		jobDetailBean.setConcurrent(false);
		return jobDetailBean.getObject();
	}
	
	public @Bean CronTriggerBean cronTrigger() {
		CronTriggerBean cronTriggerBean = new CronTriggerBean();
		cronTriggerBean.
		return cronTriggerBean;
	}
	
	public @Bean(name = "quartzScheduler")  SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setDataSource(dataSource);
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
		schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
		return schedulerFactoryBean;
	}

}
