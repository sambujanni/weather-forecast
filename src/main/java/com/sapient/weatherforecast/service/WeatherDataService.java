package com.sapient.weatherforecast.service;

import com.sapient.weatherforecast.dto.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherDataService {
    @Autowired
    WeatherDataImportService weatherDataImportService;

    @Autowired
    WeatherDataProcessor weatherDataProcessor;

    public List process(String location, Integer days) {
        Integer size = 8 * days;
        var weatherData = weatherDataImportService.importWeatherData(location, size);
        if (weatherData.isPresent()) {
           return weatherDataProcessor.process(weatherData.get());
        }
        return Collections.EMPTY_LIST;
    }
}
