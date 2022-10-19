package com.sapient.weatherforecast.web;

import com.sapient.weatherforecast.dto.TimeWindow;
import com.sapient.weatherforecast.service.WeatherDataImportService;
import com.sapient.weatherforecast.service.WeatherDataProcessor;
import com.sapient.weatherforecast.service.WeatherDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/weather")
public class WeatherController {
    @Autowired
    WeatherDataService weatherDataService;
    @GetMapping("/data/{days}")
    public ResponseEntity<List> getData(
            @RequestHeader(name = "X-COM-LOCATION", required = true, defaultValue = "LONDON") String location,
            @PathVariable Integer days
    ) {
        var weatherData = weatherDataService.process(location, days);
        return ResponseEntity.ok(weatherData);
    }
}
