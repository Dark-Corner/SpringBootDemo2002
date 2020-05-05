package com.example.demo.config;


import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 注册一个连接器
 * */
	
@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class ServletAndWeb {
	
	@Value("${spring.http}")
	private int httpPort;
	
	@Bean
	public Connector connector(){
			Connector connector = new Connector();
			connector.setScheme("http");
			connector.setPort(80);	
			return connector;
	}
	
	@Bean
	public ServletWebServerFactory servletWebServerFactory(){
		TomcatServletWebServerFactory  tamcatFactory  = new TomcatServletWebServerFactory();
		//添加额外的Tomcat连接器
		tamcatFactory.addAdditionalTomcatConnectors(connector());
		
		return 	tamcatFactory;
	}

}
