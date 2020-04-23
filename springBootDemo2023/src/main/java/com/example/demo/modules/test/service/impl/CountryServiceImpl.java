package com.example.demo.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.test.dao.CountryDao;
import com.example.demo.modules.test.entity.Country;
import com.example.demo.modules.test.service.CountryService;



@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	public CountryDao countryDao;
	
	@Override
	public Country getCountryById(int countryId) {
		return countryDao.getCountryById(countryId);
	}

	@Override
	public Country getCountryByName(String countryName) {
		return countryDao.getCountryByName(countryName);
	}
	
	

}
