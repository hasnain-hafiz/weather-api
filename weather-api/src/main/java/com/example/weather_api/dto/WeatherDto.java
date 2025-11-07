package com.example.weather_api.dto;

import lombok.Data;

@Data
public class WeatherDto {
    private String city;
    private String description;
    private double temperature;
    private int humidity;
}