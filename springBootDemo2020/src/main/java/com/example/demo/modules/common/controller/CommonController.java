package com.example.demo.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
		
	@RequestMapping("/dashboad")
	public String dashiboadPage(){
		return "index";
	}
}
