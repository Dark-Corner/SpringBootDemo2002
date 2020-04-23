package com.example.demo.modules.test.service;

import java.util.List;

import com.example.demo.modules.test.entity.City;
import com.example.demo.modules.test.entity.Country;

public interface CountryService {

	Country getCountryById(int countryId);
	
	Country getCountryByName(String countryName);

	
}
