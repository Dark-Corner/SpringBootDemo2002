package com.example.demo.modules.test.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modules.test.entity.City;
import com.example.demo.modules.test.entity.Country;
import com.example.demo.modules.test.service.CityService;
import com.example.demo.modules.test.service.CountryService;

@Controller
@RequestMapping("/test")
public class TestPageCotroller {
	
	@Autowired
	private CountryService countryService;
	@Autowired
	private CityService cityService;
	
	
	
	@RequestMapping("/index")
	public String testIndexPage(ModelMap modelMap){
		Country countryById = countryService.getCountryById(522);
		List<City> cities = cityService.getCitiesByCountryId(522);
		//取查询出来的数据前十行代码
		cities = cities.stream().limit(10).collect(Collectors.toList());
		City city = cities.get(0);
		
		
		
		modelMap.addAttribute("thymeleafTitle", "thymeleaf Title");
		modelMap.addAttribute("checked", true);
		modelMap.addAttribute("currentNumber", 99);
		modelMap.addAttribute("changeType", "checkbox");
		modelMap.addAttribute("country", countryById);
		modelMap.addAttribute("city", city);
		modelMap.addAttribute("cities", cities);
		modelMap.addAttribute("updateCityUri", "/api/city");
		modelMap.addAttribute("template", "test/index");
		return "index";
	}
}
