package com.kjlink.quartz.manager.config;

import javax.sql.DataSource;

import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.kjlink.quartz.manager.jobs.MyQuartzJobBean;
import com.kjlink.quartz.manager.service.SimpleService;

@Configuration
public class AppConfigQuartz {
	
	@Autowired
	private SimpleService simpleService;
	
	@Autowired
	private DataSource dataSource;

	public @Bean JobDetailBean jobDetail() {
		JobDetailBean jobDetailBean = new JobDetailBean();
		jobDetailBean.setJobClass(MyQuartzJobBean.class);
		JobDataMap jobDataAsMap = new JobDataMap();
		jobDataAsMap.put("simpleService", simpleService);
		jobDetailBean.setJobDataAsMap(jobDataAsMap);
		return jobDetailBean;
	}
	
	public @Bean(name = "quartzScheduler")  SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setDataSource(dataSource);
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
		schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
		return schedulerFactoryBean;
	}

}
