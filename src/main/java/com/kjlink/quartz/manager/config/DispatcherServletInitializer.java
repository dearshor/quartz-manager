package com.kjlink.quartz.manager.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class DispatcherServletInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(AppConfigQuartz.class);
		appContext.register(AppConfig.class);

		Dynamic servlet = servletContext.addServlet("main", new DispatcherServlet(appContext));
		servlet.addMapping("/");
		servlet.setAsyncSupported(true);
	}

}
