package com.sapient.weatherforecast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class City {
    private Long id;
    private String name;
    private Coord coord;
    private String country;
    private Long population;
    private Integer timezone;
    private Long sunrise;
    private Long sunset;
}
