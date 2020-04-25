package com.example.demo.modules.test.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.modules.test.entity.City;



//@Repository
@Mapper
public interface CityDao {

	/**
	 * #{countryId} ---- prepared statement, select * from m_city where country_id = ?
	 * ${countryId} ---- statement, select * from m_city where country_id = 1
	 */
	@Select("select * from m_city where country_id = #{countryId}")
	List<City> getCitiesByCountryId(int countryId);
	
	
	@Select("<script>" + 
			"select * from m_city "
			+ "	<where>"
			+ "<if test='localCityName != \"\" and localCityName != null'>"
			+ "and local_city_name = #{localCityName}"
			+ "</if>"
			+ "<if test='CityName != \"\" and CityName != null'>"
			+ "and city_name=#{CityName}"
			+ "</if>"
			+ "</where> "
			+ "limit 1"
			+ "</script>")
	City getCitiesByCityIdAndCityName(@Param("localCityName") String localCityName,@Param("CityName") String CityName);
	
	
	List<City> getCitiesByCountryId2(int countryId);
	
	
	
	
	@Insert("insert into m_city (city_name,local_city_name,country_id,date_created) "
			+ "values(#{cityName},#{localCityName},#{countryId},#{dateCreated})")
	@Options(useGeneratedKeys=true,keyColumn="city_id",keyProperty="cityId")
	void insertCity(City city);
	
	
	
	
	@Update("update m_city set local_city_name = #{localCityName} where city_id = #{cityId}")
	void updateCity(City city);
	
	
	
	@Delete("delete from m_city where city_id = #{cityId}")
	void deleteCity(int cityId);

}
