package pl.kamilszopa.weatherdemo.mapper;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.kamilszopa.weatherdemo.service.dto.ForecastDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForecastMapper {

    public List<ForecastDTO> mapToForecast(HttpResponse<JsonNode> response) {
        List<ForecastDTO> forecastDTOS = new ArrayList<>();
        try {
            JSONArray sunriseList = response.getBody().getObject().getJSONObject("daily").getJSONArray("sunrise");
            JSONArray sunsetList = response.getBody().getObject().getJSONObject("daily").getJSONArray("sunset");
            JSONArray precipitationList = response.getBody().getObject().getJSONObject("daily").getJSONArray("precipitation_sum");
            JSONArray dateList = response.getBody().getObject().getJSONObject("daily").getJSONArray("time");
            for (int i = 0; i < sunriseList.length(); i++) {
                Double precipitation = precipitationList.isNull(i) ? 0d : (Double) precipitationList.get(i);
                forecastDTOS.add(new ForecastDTO((String) dateList.get(i), (String) sunriseList.get(i), (String) sunsetList.get(i), precipitation));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return forecastDTOS;
    }
}