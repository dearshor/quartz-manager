package com.kjlink.quartz.manager.config;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.kjlink.quartz.schema.JobSchedulingData;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration @ComponentScan("com.kjlink.quartz.manager")
@PropertySource("classpath:jdbc.properties")
public class AppConfig {
	
	private @Autowired Environment env;
	
	public  @Bean ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass(env.getProperty("jdbc,driver.className"));
		comboPooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		comboPooledDataSource.setUser(env.getProperty("jdbc.user.name"));
		comboPooledDataSource.setPassword(env.getProperty("jdbc.user.password"));
		comboPooledDataSource.setInitialPoolSize(Integer.valueOf(env.getProperty("cpool.minPoolSize")) );
		comboPooledDataSource.setMinPoolSize(Integer.valueOf( env.getProperty("cpool.minPoolSize")));
		comboPooledDataSource.setMaxPoolSize(Integer.valueOf( env.getProperty("cpool.maxPoolSize"  )));
		comboPooledDataSource.setAcquireIncrement(Integer.valueOf( env.getProperty("cpool.acquireIncrement")));
		comboPooledDataSource.setMaxIdleTime(Integer.valueOf(env.getProperty("cpool.maxIdleTime")));
		return comboPooledDataSource;
	}
	
	public @Bean Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(JobSchedulingData.class);
		marshaller.setSchema(new ClassPathResource("job_scheduling_data_2_0.xsd"));
		return marshaller;
	}
	
}
