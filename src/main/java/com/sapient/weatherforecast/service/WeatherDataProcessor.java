package com.sapient.weatherforecast.service;

import com.sapient.weatherforecast.dto.PredictionForWindow;
import com.sapient.weatherforecast.dto.TimeWindow;
import com.sapient.weatherforecast.dto.WeatherData;
import com.sapient.weatherforecast.dto.WeatherDataPerDay;
import com.sapient.weatherforecast.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class WeatherDataProcessor {

    @Value("${properties.temp-limit}")
    Double tempLimit;
    public List process(WeatherData weatherData) {
        List<TimeWindow> timeWindows = new ArrayList<>();
        var integerListMap = weatherData.getList().stream()
                .collect(groupingBy(window -> window.getDt()
                        .atZone(ZoneId.of("Europe/London")).getDayOfMonth()));
        var weatherDataPerDays = integerListMap.entrySet().stream().limit(4)
                .map(pair -> constructObject(pair.getValue()))
                .collect(Collectors.toList());
        return weatherDataPerDays.size() > 0 ? weatherDataPerDays : Collections.EMPTY_LIST;
    }

    private WeatherDataPerDay constructObject(List<TimeWindow> timeWindows) {
        WeatherDataPerDay weatherDataPerDay = new WeatherDataPerDay();
        weatherDataPerDay.setDate(LocalDate.ofInstant(timeWindows.get(0).getDt(), ZoneId.of("Europe/London")));
        weatherDataPerDay.setMaxTemp(timeWindows.stream()
                .collect(Collectors.maxBy(Comparator.comparing(win -> win.getMain().getTemp_max()))).get()
                .getMain().getTemp_max());
        weatherDataPerDay.setMinTemp(timeWindows.stream()
                .collect(Collectors.minBy(Comparator.comparing(win -> win.getMain().getTemp_min()))).get()
                .getMain().getTemp_min());
        weatherDataPerDay.setWindows(timeWindows.stream().map(win ->constructPredictionPerWindow(win)).collect(Collectors.toList()));
        return weatherDataPerDay;
    }

    private PredictionForWindow constructPredictionPerWindow(TimeWindow timeWindow) {
        PredictionForWindow predictionForWindow = new PredictionForWindow();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.UK).withZone(ZoneId.of("Europe/London"));
        predictionForWindow.setTime(formatter.format(timeWindow.getDt()));
        predictionForWindow.setTemp(timeWindow.getMain().getTemp());
        if (timeWindow.getRain() != null)
            predictionForWindow.setRainFallPrediction(Constants.RAIN_FALL_PREDICTION);
        if (timeWindow.getMain().getTemp_max() > tempLimit)
            predictionForWindow.setHighTempPrediction(Constants.HIGH_TEMP_PREDICTION);
        return predictionForWindow;
    }

}

