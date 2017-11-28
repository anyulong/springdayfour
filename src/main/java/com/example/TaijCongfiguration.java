package com.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.FooProperties.Security;

@Configuration
public class TaijCongfiguration {
	
	@Bean
	public FooProperties fooproperties() {
		Security security = new Security();
		FooProperties fooproperties = new FooProperties(security);
		return fooproperties;
	}
}
