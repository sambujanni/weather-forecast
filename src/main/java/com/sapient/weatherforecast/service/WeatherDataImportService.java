package com.sapient.weatherforecast.service;

import com.sapient.weatherforecast.dto.TimeWindow;
import com.sapient.weatherforecast.dto.WeatherData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class WeatherDataImportService {

    @Autowired
    WeatherDataProcessor weatherDataProcessor;

    @Value("${properties.base-url}")
    String baseUrl;
    private Map<String, WeatherData> weatherDataMap = new ConcurrentHashMap<>();

    public Optional<WeatherData> importWeatherData(String location, int size) {
        WeatherData weatherData = null;
        String key = "d2929e9483efc82c82c32ee7e02d563e";
        String uri = String.format(baseUrl + "q=%s&appid=%s&cnt=%d", location, key, size);
        RestTemplate restTemplate = new RestTemplate();
        try {
            weatherData = restTemplate.exchange(uri, HttpMethod.GET, null, WeatherData.class).getBody();
        } catch (RestClientException e) {
            log.error("Failed importing weather data with error {}", e.getLocalizedMessage());
        }
        return Optional.of(weatherData);
    }
}
