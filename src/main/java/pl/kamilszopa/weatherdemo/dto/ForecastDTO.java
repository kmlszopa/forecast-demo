package pl.kamilszopa.weatherdemo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ForecastDTO {
    private String date;
    private String sunrise;
    private String sunset;
    private Double precipitation;

    public ForecastDTO(String date, String sunrise, String sunset, Double precipitation) {
        this.date = date;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.precipitation = precipitation;
    }

    public String getDate() {
        return date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public Double getPrecipitation() {
        return precipitation;
    }
}
