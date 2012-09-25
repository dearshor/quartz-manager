package com.kjlink.quartz.manager.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;

@Controller
public class JobController {

	private @Autowired
	@Qualifier("quartzScheduler")
	Scheduler scheduler;

	private @Autowired
	JobDetail jobDetail;

	public void add() {
		scheduler.addJob(jobDetail, true);
	}

	@RequestMapping(consumes = "application/json" )
	@ResponseBody 
	public List<Trigger> query() {
		Trigger[] triggersOfJob = scheduler.getTriggersOfJob(
				jobDetail.getName(), Scheduler.DEFAULT_GROUP);
		List<Trigger> trigList = Arrays.asList(triggersOfJob);
		return trigList;
	}

	public void pause() {
		// TODO Auto-generated method stub

	}

	public void resume() {
		// TODO Auto-generated method stub

	}

	public void remove() {
		// TODO Auto-generated method stub

	}

}
