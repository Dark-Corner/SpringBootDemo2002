package com.example.demo.modules.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;

import com.example.demo.modules.common.vo.Result;
import com.example.demo.modules.test.entity.City;
import com.github.pagehelper.PageInfo;


public interface CityService {

	List<City> getCitiesByCountryId(int countryId);
	
	List<City> getCitiesByCountryId2(int countryId);
	
	PageInfo<City> getCitiesByPage(int currenPage, int pageSize, int countryId);
	
	Result<City> insertCity(City city);
	
	
	Result<City> updateCity(City city);
	
	
	Result<Object> deleteCity(int cityId);
	
	
	City getCitiesByCityIdAndCityName(String cityId,String CityName);
}
