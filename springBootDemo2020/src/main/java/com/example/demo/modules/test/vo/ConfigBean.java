package com.example.demo.modules.test.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/*
 * @Component将这个类注册为spring组件
 * */
@Component
//导入非全局性对应的对象
@PropertySource("classpath:config/applicationTest.properties")
//用于配置属性同时下面创建属性是不用在像全局配置文件一样创建value因为有匹配到对象的前缀
@ConfigurationProperties(prefix = "com.thron.bird")
public class ConfigBean {
		
	private int port;
	private String name;
	private int age;
	private String desc;
	private String random;
	
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	
	
}
