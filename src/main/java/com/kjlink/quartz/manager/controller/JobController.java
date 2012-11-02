package com.kjlink.quartz.manager.controller;

import java.util.Arrays;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/schedule/")
public class JobController {

	@Autowired
	@Qualifier("quartzScheduler")
	private 	Scheduler scheduler;

	private @Autowired JobDetail jobDetail;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add() {
		try {
			scheduler.addJob(jobDetail, true);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException(e);
		}
	}

	@RequestMapping(value = "query/{jobGroup}/{jobName}", method = RequestMethod.GET, consumes = "application/json")
	@ResponseBody
	public List<Trigger> query(@PathVariable String jobName, @PathVariable String jobGroup) {
		Trigger[] triggersOfJob;
		try {
			triggersOfJob = scheduler.getTriggersOfJob(jobName, jobGroup);
		} catch (SchedulerException e) {
			throw new IllegalStateException(e);
		}
		return Arrays.asList(triggersOfJob);
	}

	@RequestMapping(value = "pause/{groupName}/{triggerName}", method = RequestMethod.GET)
	public void pause(@PathVariable String groupName, @PathVariable String triggerName) {
		try {
			scheduler.pauseTrigger(triggerName, groupName);
		} catch (SchedulerException e) {
			throw new IllegalStateException(e);
		}
	}

	@RequestMapping(value = "resume/{groupName}/{triggerName}", method = RequestMethod.GET)
	public void resume(@PathVariable String groupName, @PathVariable String triggerName) {
		try {
			scheduler.resumeTrigger(triggerName, groupName);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException(e);
		}
	}

	@RequestMapping(value = "remove/{groupName}/{triggerName}", method = RequestMethod.GET)
	public void remove(@PathVariable String groupName, @PathVariable String triggerName) {
		// TODO Auto-generated method stub
		try {
			scheduler.unscheduleJob(triggerName, groupName);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException(e);
		}
	}

}
