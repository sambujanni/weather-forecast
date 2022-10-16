package com.sapient.weatherforecast;

import com.sapient.weatherforecast.dto.WeatherData;
import com.sapient.weatherforecast.scheduler.ImportWeatherDataScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    ImportWeatherDataScheduler puller;
    @GetMapping("/data")
    public ResponseEntity<WeatherData> getData() {
        return ResponseEntity.ok(puller.findByCityId(2643743L));
    }
}
