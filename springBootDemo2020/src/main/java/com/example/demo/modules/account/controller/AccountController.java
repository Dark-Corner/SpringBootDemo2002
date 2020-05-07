package com.example.demo.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	
	/*
	 * 127.0.0.1/account/login
	 * */
	@RequestMapping("/login")
	public String loginPage(){
		return "indexSimple";
	}
	/*
	 * 127.0.0.1/account/users
	 * */
	@RequestMapping("/users")
	public String userPage(){
		return "index";
	}
	
	@RequestMapping("/roles")
	public String rolePage(){
		return "index";
	}
	
}
