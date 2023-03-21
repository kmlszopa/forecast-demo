package pl.kamilszopa.weatherdemo.service;

import org.springframework.stereotype.Service;
import pl.kamilszopa.weatherdemo.dto.ForecastDTO;

import java.util.List;

@Service
public class ForecastService {


    private final ForecastApiServiceInterface forecastService;

    public ForecastService(ForecastApiServiceInterface forecastService) {
        this.forecastService = forecastService;
    }

    public List<ForecastDTO> getHistoricalForecast(String latitude, String longitude) {
        return forecastService.getHistoricalForecast(latitude, longitude);
    }
}
