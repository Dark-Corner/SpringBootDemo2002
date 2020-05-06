package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.filter.ParameterFilter;
import com.example.demo.interceptor.UrlInterceptor;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private UrlInterceptor  urlInterceptor;
	
	@Bean
	public FilterRegistrationBean<ParameterFilter> filterRegistrationBean(){
		FilterRegistrationBean<ParameterFilter> FilterRegister = new FilterRegistrationBean<>();
		FilterRegister.setFilter(new ParameterFilter());
		return FilterRegister;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	
	
	
}
