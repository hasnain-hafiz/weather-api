package com.example.weather_api.service;


import com.example.weather_api.config.WeatherProperties;
import com.example.weather_api.dto.WeatherDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherProperties props;

    public WeatherService(WeatherProperties props) {
        this.restTemplate = new RestTemplate();
        this.props = props;
    }

    public WeatherDto getCurrentWeather(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(props.getBaseUrl() + "/weather")
                .queryParam("q", city)
                .queryParam("appid", props.getApiKey())
                .queryParam("units", "metric")
                .toUriString();

        // Fetch data from OpenWeatherMap
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Extract relevant data
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        Map<String, Object> weather0 = ((java.util.List<Map<String, Object>>) response.get("weather")).get(0);

        WeatherDto dto = new WeatherDto();
        dto.setCity((String) response.get("name"));
        dto.setDescription((String) weather0.get("description"));
        dto.setTemperature(Double.parseDouble(main.get("temp").toString()));
        dto.setHumidity((Integer) main.get("humidity"));
        return dto;
    }
}