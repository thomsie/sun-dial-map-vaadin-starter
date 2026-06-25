package com.thomsie.sundial.service;

import com.thomsie.sundial.domain.Coordinates;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class AstronomyServiceTest {
    private final AstronomyService astronomyService = new AstronomyService();

    @Test
    void shouldReturnSunPosition() {
        var result = astronomyService.calculateSunPosition(new Coordinates(47.3769, 8.5417), LocalDateTime.of(2026, 6, 25, 12, 0));
        assertThat(result.azimuth()).isBetween(0.0, 360.0);
        assertThat(result.sunrise()).isNotBlank();
        assertThat(result.sunset()).isNotBlank();
    }
}
