package pl.kamilszopa.weatherdemo.service;

import pl.kamilszopa.weatherdemo.service.dto.ForecastDTO;

import java.util.List;

public interface ForecastApiServiceInterface {
    List<ForecastDTO> getHistoricalForecast(String latitude, String longitude);
}
