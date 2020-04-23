package com.example.demo.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.common.vo.Result;
import com.example.demo.modules.common.vo.Result.ResultEnum;
import com.example.demo.modules.test.dao.CityDao;
import com.example.demo.modules.test.entity.City;
import com.example.demo.modules.test.service.CityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityDao cityDao;

	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		List<City> cities = cityDao.getCitiesByCountryId(countryId);
		return cities;
	}

	@Override
	public List<City> getCitiesByCountryId2(int countryId) {
		List<City> cities = cityDao.getCitiesByCountryId2(countryId);
		return cities;
	}
	
	@Override
	public PageInfo<City> getCitiesByPage(int currenPage, int pageSize, int countryId) {
		PageHelper.startPage(currenPage, pageSize);
		List<City> cities = cityDao.getCitiesByCountryId2(countryId);
		return new PageInfo<City>(cities);
	}

	@Override
	public Result<City> insertCity(City city) {
		Result<City> result = new Result<>(ResultEnum.SUCCESS.status,"insert success!!!!");
		try {
			cityDao.insertCity(city);
			result.setObjiect(city);
		} catch (Exception e) {
			result.setStatus(ResultEnum.FAILD.status);
			result.setMessage(e.getMessage());//获取错误异常进行抛出行为
		}
		return result;
	}

	
	
	
}
