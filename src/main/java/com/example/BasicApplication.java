package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.ServletRequestHandledEvent;




@SpringBootApplication
public class BasicApplication {
	private static final Logger log = LoggerFactory.getLogger(BasicApplication.class);
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(BasicApplication.class, args);
		System.out.println(context.getBean(FooProperties.class));
//		FooProperties fooProperties = context.getBean(FooProperties.class);
//		System.out.println(fooProperties.getArray().get(0));
	}
	
	
	@Bean
	public HealthIndicator HealthTestLam() {
		return () -> {
			return Health.down().withDetail("Error Code", 404).build();
		};
	}
	
	@Autowired
	private CounterService counterService;
	
	@Bean
	public ApplicationListener<ApplicationEvent> helloListener() {
		final String HELLO_URL = "/xyz";

		return (ApplicationEvent event) -> {
			if (event instanceof ServletRequestHandledEvent) {
				ServletRequestHandledEvent e = (ServletRequestHandledEvent) event;
				if (e.getRequestUrl().equals(HELLO_URL))
					counterService.increment("xyz.hits");
			}
		};
	}
}	
