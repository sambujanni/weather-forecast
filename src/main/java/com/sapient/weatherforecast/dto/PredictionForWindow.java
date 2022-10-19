package com.sapient.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PredictionForWindow {
    private String time;
    private Double temp;
    private String rainFallPrediction;
    private String highTempPrediction;
}
