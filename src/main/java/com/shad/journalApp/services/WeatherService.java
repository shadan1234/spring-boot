package com.shad.journalApp.services;

import com.shad.journalApp.api.response.WeatherResponse;
import com.shad.journalApp.cache.AppCache;
import com.shad.journalApp.constants.PlaceHolder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;


    @Autowired
    private AppCache appCache;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null) {
            return weatherResponse;
        }else {
            String finalUrl = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolder.CITY, city).replace(PlaceHolder.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null) {
                redisService.set("weather_of_" + city,body,300l);
            }
            return body;
        }
    }
}
