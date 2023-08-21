package com.system.WeatherApplication.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.WeatherApplication.entity.WeatherData;


@Service
public class WeatherService {
	
    private final String apiKey = "11a3c96d31ed5cc5ed43335b994736fb";
    private final String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Map<String,String> getWeatherData(String city,String unit) {
        String apiUrl = weatherApiUrl + "?q=" + city +"&units="+unit +"&appid=" + apiKey;

        try {
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
            
            WeatherData temp = objectMapper.readValue(jsonResponse, WeatherData.class);
            return getData(temp,unit);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
	
	public Map<String,String> getData(WeatherData obj,String unit)
	{
		Map<String,String> weatherData = new HashMap<>();
		if(unit.equals("imperial"))
		weatherData.put("Temp", Double.toString(obj.getMain().getTemp())+" F");
		else
			weatherData.put("Temp", Double.toString(obj.getMain().getTemp())+" C");

		weatherData.put("Humidity", Integer.toString(obj.getMain().getHumidity())+"%");
		weatherData.put("Weather",obj.getWeather().iterator().next().getMain());
		weatherData.put("Wind",obj.getWind().getSpeed()+"Km/hr");
		
		return weatherData;
	}
	
	
}
