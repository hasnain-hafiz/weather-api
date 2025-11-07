package com.example.weather_api.controller;


import com.example.weather_api.dto.WeatherDto;
import com.example.weather_api.service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherDto getWeather(@RequestParam String city) {
        return weatherService.getCurrentWeather(city);
    }
}
