package com.example.demo.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.test.vo.ConfigBean;

@RestController
@RequestMapping("/api/Controller")
public class TestController {
	
	//引入log
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	
	
	//这是用全局配置文件 测试
	//通过value这个注解的方式将值传递过来
	@Value("${server.port}")
	private int port;
	@Value("${com.thron_bird.name}")
	private String name;
	@Value("${com.thron_bird.age}")
	private int age;
	@Value("${com.thron_bird.desc}")
	private String desc;
	@Value("${com.thron_bird.random}")
	private String random;
	
	
	//注入我们创建的非全局配置文件测试类
	@Autowired
	private ConfigBean configBean;
	
	@RequestMapping("/log")
	public String logTest(){
		LOGGER.trace("this is TRACE");
		LOGGER.debug("this is DEBUG");
		LOGGER.info("this is INFO");
		LOGGER.warn("this is WARN");
		LOGGER.error("this is ERROR log.");
		return "this is log test";
	}

	@RequestMapping("/config")
	public String configTest(){
		StringBuffer sb = new StringBuffer();
		sb.append(port).append("------")
			.append(name).append("------")
			.append(age).append("------")
			.append(desc).append("------")
			.append(random).append("</br>");
			sb.append(configBean.getName()).append("------")
				.append(configBean.getAge()).append("------")
				.append(configBean.getDesc()).append("------")
				.append(configBean.getRandom());
		return sb.toString();
	}
	
	@RequestMapping("/appDoesc")
	//@ResponseBody的作用是讲该方法变成接口与controller一样
	@ResponseBody
	public String getDoesc(){
		return "Holle word, this is spring boot demo.";
	}
}
