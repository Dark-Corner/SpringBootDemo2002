package com.example.demo.modules.test.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.common.vo.Result;
import com.example.demo.modules.test.entity.City;
import com.example.demo.modules.test.service.CityService;
import com.github.pagehelper.PageInfo;



@RestController
@RequestMapping("/api")
public class CityController {
	
	@Autowired
	private CityService cityService;

	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
		return cityService.getCitiesByCountryId(countryId);
	}
/*
 * 127.0.0.1/api/cities?currenPage=1&pageSize=10&countryId=522
 * */
	@RequestMapping("/cities")
	public PageInfo<City> getCitiesByPage(@RequestParam int currenPage,
			@RequestParam  int pageSize,@RequestParam  int countryId) {
		return cityService.getCitiesByPage(currenPage, pageSize, countryId);
	}
	
	
	/*
	 * 127.0.0.1/api/city?CityName=Chin
	 * */
	@RequestMapping("/city")
	public City getCitiesByCityIdAndCityName(@RequestParam(required=false) String localCityName,@RequestParam(required=false) String CityName) {
		return cityService.getCitiesByCityIdAndCityName(localCityName, CityName);
	}
	
	
	@PostMapping(value="/city",consumes="application/josn")
	public Result<City> insertCity(@RequestBody City city){
		return cityService.insertCity(city);
	}
	
	
	@PutMapping(value="/city",consumes="application/x-www-form-urlencoded")
	public Result<City> updateCity(@ModelAttribute City city){
		return cityService.updateCity(city);
	}
	
	
	@DeleteMapping("/city/{cityId}")
	public Result<Object> deleteCity(@PathVariable int  cityId){
		return cityService.deleteCity(cityId);
	}
}
