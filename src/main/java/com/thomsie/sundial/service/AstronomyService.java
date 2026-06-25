package com.thomsie.sundial.service;

import com.thomsie.sundial.domain.Coordinates;
import com.thomsie.sundial.domain.MoonPosition;
import com.thomsie.sundial.domain.SunPosition;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AstronomyService {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public SunPosition calculateSunPosition(Coordinates coordinates, LocalDateTime dateTime) {
        double pseudoHour = dateTime.getHour() + (dateTime.getMinute() / 60.0);
        double azimuth = ((pseudoHour / 24.0) * 360.0 + 180.0) % 360.0;
        double elevation = Math.max(-10, 60 - Math.abs(12 - pseudoHour) * 8);
        return new SunPosition(round(azimuth), round(elevation), dateTime.withHour(6).withMinute(0).format(TIME_FORMAT), dateTime.withHour(20).withMinute(0).format(TIME_FORMAT));
    }

    public MoonPosition calculateMoonPosition(Coordinates coordinates, LocalDateTime dateTime) {
        double pseudoHour = dateTime.getHour() + (dateTime.getMinute() / 60.0);
        double azimuth = ((pseudoHour / 24.0) * 360.0 + 45.0) % 360.0;
        double elevation = Math.max(-20, 35 - Math.abs(1 - (pseudoHour / 24.0)) * 45);
        double illumination = (Math.sin(dateTime.getDayOfMonth()) + 1) / 2.0;
        return new MoonPosition(round(azimuth), round(elevation), round(illumination * 100));
    }

    private double round(double value) {
        return Math.round(value * 10.0) / 10.0;
    }
}
