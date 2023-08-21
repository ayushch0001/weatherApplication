package com.system.WeatherApplication.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.system.WeatherApplication.service.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherApplicationController {
	

	
	@Autowired
	WeatherService ser;
	
	
	@RequestMapping("/home")
	public String showData()
	{
		return "WeatherData";
	}
	
	@RequestMapping("/data")
	public String showVal(@RequestParam("city")String city,@RequestParam("unit")String unit,Model model)
	{
		Map<String,String> data;
		if(unit.equals("F"))
		 data = ser.getWeatherData(city,"imperial");
		else
			data=ser.getWeatherData(city,"metric");
		model.addAttribute("weatherData", data);
		
		return "WeatherData";
	}

}
