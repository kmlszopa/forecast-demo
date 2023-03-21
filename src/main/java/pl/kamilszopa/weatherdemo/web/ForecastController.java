package pl.kamilszopa.weatherdemo.web;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilszopa.weatherdemo.service.ForecastService;
import pl.kamilszopa.weatherdemo.dto.ForecastDTO;

import java.util.List;

@RestController
@RequestMapping("/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/historical")
    public List<ForecastDTO> historicalWeatherForecast(@NotBlank @RequestParam String latitude, @NotBlank @RequestParam String longitude){
        return forecastService.getHistoricalForecast(latitude, longitude);
    }

}
