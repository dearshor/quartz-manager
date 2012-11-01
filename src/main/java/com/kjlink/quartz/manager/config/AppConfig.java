package com.kjlink.quartz.manager.config;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration @ComponentScan("com.kjlink.quartz.manager")
@PropertySource("classpath:jdbc.properties")
public class AppConfig {
	
	private @Autowired Environment env;
	
	public  @Bean ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
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
	
}
