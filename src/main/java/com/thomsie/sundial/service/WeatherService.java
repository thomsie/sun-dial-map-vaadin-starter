package com.thomsie.sundial.service;

import com.thomsie.sundial.domain.Coordinates;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final OpenMeteoClient openMeteoClient;

    public WeatherService(OpenMeteoClient openMeteoClient) {
        this.openMeteoClient = openMeteoClient;
    }

    public String getCloudSummary(Coordinates coordinates) {
        try {
            String raw = openMeteoClient.fetchCloudData(coordinates.latitude(), coordinates.longitude());
            return raw == null || raw.isBlank() ? "No cloud data available." : "Cloud data loaded.";
        } catch (Exception ex) {
            return "Cloud data currently unavailable.";
        }
    }
}
