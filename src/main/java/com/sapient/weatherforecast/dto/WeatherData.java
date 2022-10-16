package com.sapient.weatherforecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private String cod;
    private String message;
    private Integer cnt;
    private List<TimeWindow> list;
    private City city;
}
