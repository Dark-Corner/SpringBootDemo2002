package com.example.demo.modules.test.service;

import com.example.demo.modules.test.entity.Country;

public interface CountryService {

	Country getCountryById(int countryId);
	
	Country getCountryByName(String countryName);

	
}
