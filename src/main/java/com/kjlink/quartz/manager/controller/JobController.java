package com.kjlink.quartz.manager.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;

@Controller
@RequestMapping("/schedule/")
public class JobController {

	@Autowired
	@Qualifier("quartzScheduler")
	private 	Scheduler scheduler;

	private @Autowired JobDetail jobDetail;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add() {
		scheduler.addJob(jobDetail, true);
	}

	@RequestMapping(value = "query/{jobGroup}/{jobName}", method = RequestMethod.GET, consumes = "application/json")
	@ResponseBody
	public List<Trigger> query(@PathVariable String jobName, @PathVariable String jobGroup) {
		Trigger[] triggersOfJob = scheduler.getTriggersOfJob(
				jobName, jobGroup);
		List<Trigger> trigList = Arrays.asList(triggersOfJob);
		return trigList;
	}

	@RequestMapping(value = "pause/{groupName}/{triggerName}", method = RequestMethod.GET)
	public void pause(@PathVariable String groupName, @PathVariable String triggerName) {
		scheduler.pauseTrigger(triggerName, groupName);
	}

	@RequestMapping(value = "resume/{groupName}/{triggerName}", method = RequestMethod.GET)
	public void resume(@PathVariable String groupName, @PathVariable String triggerName) {
		// TODO Auto-generated method stub
		scheduler.resumeTrigger(triggerName, groupName);
	}

	@RequestMapping(value = "remove/{groupName}/{triggerName}", method = RequestMethod.GET)
	public void remove(@PathVariable String groupName, @PathVariable String triggerName) {
		// TODO Auto-generated method stub
		scheduler.unscheduleJob(triggerName, groupName);
	}

}
