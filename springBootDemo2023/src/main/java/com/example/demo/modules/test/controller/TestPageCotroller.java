package com.example.demo.modules.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
	
	
	
	
	@PostMapping(value="/files",consumes="multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile[] files,
			RedirectAttributes redirectAttributes){
		
		boolean isEmpty = true;
		
		try {
			
			for(MultipartFile file : files){
				if(file.isEmpty()){
					continue;
				}
				String fileName = file.getOriginalFilename();
				String destFilePath = "D:\\load\\"+fileName;
				//在目标文件创建一个空的完后再通过file将数据以流的方式替换掉
				File destFile = new File(destFilePath);	
				file.transferTo(destFile);
				
				
				isEmpty = false;
				
				
				// 使用工具类Files来上传文件
//				byte[] bytes = file.getBytes();
//				Path path = Paths.get(destFileName);
//				Files.write(path, bytes);
				
				
			}		
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();				
			redirectAttributes.addFlashAttribute("message", "Upload file failed.");
			return "redirect:/test/index";
		}
		
		if(isEmpty){
			redirectAttributes.addFlashAttribute("message", "Please select file.");
		}else{
			redirectAttributes.addFlashAttribute("message", "Upload file success.");
		}
		return "redirect:/test/index";
	}
	
	
	
	
	@PostMapping(value="/file",consumes="multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file,
			RedirectAttributes redirectAttributes){
		
		if(file.isEmpty()){
			redirectAttributes.addFlashAttribute("message", "Please select file.");
			return "redirect:/test/index";
		}
		
		
		try {
			
			String fileName = file.getOriginalFilename();
			String destFilePath = "D:\\load\\"+fileName;
			//在目标文件创建一个空的    完后再通过file将数据以流的方式替换掉
			File destFile = new File(destFilePath);	
			file.transferTo(destFile);
			
			redirectAttributes.addFlashAttribute("message", "Upload file success.");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			redirectAttributes.addFlashAttribute("message", "Upload file failed.");
			return "redirect:/test/index";
		}
		return "redirect:/test/index";
	}
	
	
	
	/*
	 * 127.0.0.1/test/index
	 * */
	
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
//		modelMap.addAttribute("template", "test/index");
		return "index";
	}
}

