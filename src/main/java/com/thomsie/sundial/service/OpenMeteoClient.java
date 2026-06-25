package com.thomsie.sundial.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OpenMeteoClient {
    private final RestClient restClient;

    public OpenMeteoClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://api.open-meteo.com").build();
    }

    public String fetchCloudData(double latitude, double longitude) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("hourly", "cloud_cover")
                        .queryParam("forecast_days", 1)
                        .build())
                .retrieve()
                .body(String.class);
    }
}
