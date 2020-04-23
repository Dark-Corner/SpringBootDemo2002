package com.example.demo.modules.test.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.modules.test.entity.Country;
import com.example.demo.modules.test.service.CountryService;



@RestController
@RequestMapping("/api")
public class CountryController {
	
	@Autowired
	private CountryService countryService;

	@RequestMapping("/countrynName")
	public Country getCountryByName(@RequestParam String countryname) {
		return countryService.getCountryByName(countryname);
	}
	
	
	@RequestMapping("/countryId/{countryId}")
	public Country getCountryById(@PathVariable int countryId) {
		return countryService.getCountryById(countryId);
	}
	
	
}
