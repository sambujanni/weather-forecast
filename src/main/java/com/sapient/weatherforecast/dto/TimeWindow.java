package com.sapient.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeWindow {
    private Long dt;
    private Main main;
    private List<Weather> weather;
    private Cloud clouds;
    private Wind wind;
    private Integer visibility;
    private Double pop;
    private Rain rain;
    private Sys sys;
    private String dt_txt;
}
