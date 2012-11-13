package com.dearshor.research.testdrive;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.myschema.CronTriggerType;
import com.example.myschema.JobDetailType;
import com.example.myschema.JobSchedulingData;
import com.example.myschema.PreProcessingCommandsType;
import com.example.myschema.ProcessingDirectivesType;
import com.example.myschema.TriggerType;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringMarshallingTests {

	private static final String FILE_NAME = "job-data.xml";
	private String settingsDir;
	private File xmlFile;

	@Autowired
	private MarshallingService marshallingService;

	private JobSchedulingData jobSchedulingData;
	private static final Log logger = LogFactory.getLog(SpringMarshallingTests.class);

	@PostConstruct
	public void setup() {
		jobSchedulingData = new JobSchedulingData();
		JobSchedulingData.Schedule scheduleData = new JobSchedulingData.Schedule();
		JobDetailType job1 = new JobDetailType("job1"); 
		job1.setGroup("DEFAULT");
		job1.setJobClass("com.dearshor.research.jobs.mock.FooJob");
		job1.setDurability(Boolean.TRUE);
		job1.setDescription("Foo job");
		job1.setRecover(Boolean.TRUE);
		TriggerType trigger = new TriggerType();
		CronTriggerType cronTrigger1 = new CronTriggerType("* * * */2 *");
		cronTrigger1.setName("trigger1");
		trigger.setCron(cronTrigger1) ;
		scheduleData.addContent(job1);//.addContent(trigger);
		jobSchedulingData.addContent(new PreProcessingCommandsType())
				.addContent(new ProcessingDirectivesType())
				.addContent(scheduleData);
		
		if (StringUtils.isEmpty(settingsDir)) {
			File defaultSettingsDir = new File(new StringBuilder(
					System.getProperty("user.dir")).append(File.separator)
					.append("target").append(File.separator).append("conf")
					.toString());
			xmlFile = new File(defaultSettingsDir, FILE_NAME);
		} else {
			xmlFile = new File(new StringBuilder(settingsDir)
					.append(File.separator).append(FILE_NAME).toString());
		}
		if (!xmlFile.getParentFile().exists()) {
			xmlFile.getParentFile().mkdirs();
		}
	}

	@Test
	public void testSaveSettings() throws IOException {
		marshallingService.saveSettings(xmlFile, jobSchedulingData);
		assertTrue(xmlFile.exists());
		System.out.printf("生成的xml文件：%s%s",xmlFile.getAbsoluteFile(), System.getProperty("line.separator"));
	}

	@Test
	public void testLoadSettings() throws IOException {
		JobSchedulingData jobSchedulingData = marshallingService.loadSettings(xmlFile, this.jobSchedulingData);
		assertNotNull(jobSchedulingData);
		logger.debug(jobSchedulingData.toString()); // TODO 为何不起作用？
		System.out.printf("逆向生成的jobSchedulingData：%s%s", jobSchedulingData.toString(), System.getProperty("line.separator"));
	}

}
