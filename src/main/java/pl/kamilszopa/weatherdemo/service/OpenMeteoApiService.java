package pl.kamilszopa.weatherdemo.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.kamilszopa.weatherdemo.domain.RequestDetails;
import pl.kamilszopa.weatherdemo.mapper.ForecastMapper;
import pl.kamilszopa.weatherdemo.repository.RequestDetailsRepository;
import pl.kamilszopa.weatherdemo.service.dto.ForecastDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

@Service
public class OpenMeteoApiService implements ForecastApiServiceInterface {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ForecastMapper forecastMapper;
    private final RequestDetailsRepository requestDetailsRepository;

    public OpenMeteoApiService(ForecastMapper forecastMapper, RequestDetailsRepository requestDetailsRepository) {
        this.forecastMapper = forecastMapper;
        this.requestDetailsRepository = requestDetailsRepository;
    }

    @Override
    public List<ForecastDTO> getHistoricalForecast(String latitude, String longitude) {
        Unirest.setTimeouts(0, 0);
        String url = buildUrl(latitude, longitude);
        List<ForecastDTO> forecastDTOS;
        try {
            HttpResponse<JsonNode> response = Unirest.get(url)
                    .asJson();
            forecastDTOS = forecastMapper.mapToForecast(response);
        } catch (UnirestException e) {
            LOGGER.error("Failed to GET data. URL = " + url);
            throw new RuntimeException(e);
        }
        requestDetailsRepository.save(new RequestDetails(LocalDateTime.now(),Float.valueOf(latitude), Float.valueOf(longitude)));
        return forecastDTOS;
    }

    private static String buildUrl(String latitude, String longitude) {
        StringBuilder sb = new StringBuilder();
        return sb.append("https://archive-api.open-meteo.com/v1/archive?")
                .append("latitude=")
                .append(latitude)
                .append("&longitude=")
                .append(longitude)
                .append("&start_date=")
                .append(LocalDate.now().minusWeeks(1))
                .append("&end_date=")
                .append(LocalDate.now())
                .append("&daily=sunrise%2Csunset%2")
                .append("Cprecipitation_sum")
                .append("&timezone=")
                .append(TimeZone.getDefault().getID())
                .toString();
    }
}
