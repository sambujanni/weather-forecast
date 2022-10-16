package com.sapient.weatherforecast.scheduler;

import com.sapient.weatherforecast.dto.WeatherData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ImportWeatherDataScheduler {

    @Value("${base-url}")
    String baseUrl;
    private Map<Long, WeatherData> weatherDataMap = new ConcurrentHashMap<>();

    @Scheduled(fixedRate = 2 * 60 * 1000)
    private void pull() {
        String city = "london";
        String uri = "https://api.openweathermap.org/data/2.5/forecast?q=london&appid=d2929e9483efc82c82c32ee7e02d563e&cnt=5";
        RestTemplate restTemplate = new RestTemplate();
        try {
            WeatherData weatherData = restTemplate.exchange(baseUrl, HttpMethod.GET, null, WeatherData.class).getBody();
            if (weatherData.getCod().equals("200")) {
                Long cityId = weatherData.getCity().getId();
                weatherDataMap.put(cityId, weatherData);
                log.info("data {}", cityId);
            }
        } catch (RestClientException e) {
            log.error("Failed importing weather data with error {}", e.getLocalizedMessage());
        }

    }
    public WeatherData findByCityId(Long id) {
        return weatherDataMap.get(id);
    }

}
