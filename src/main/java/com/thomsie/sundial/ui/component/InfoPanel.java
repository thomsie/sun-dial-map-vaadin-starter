package com.thomsie.sundial.ui.component;

import com.thomsie.sundial.domain.MoonPosition;
import com.thomsie.sundial.domain.SunPosition;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class InfoPanel extends VerticalLayout {
    private final Paragraph summary = new Paragraph("No calculation yet.");
    private final Div content = new Div();

    public InfoPanel() {
        setPadding(false);
        setSpacing(false);
        setWidthFull();
        H3 title = new H3("Astronomical data");
        Details details = new Details("Details", content);
        details.setOpened(true);
        add(title, summary, details);
    }

    public void showSun(SunPosition sunPosition) {
        summary.setText("Sun data updated.");
        content.removeAll();
        content.add(new Paragraph("Azimuth: " + sunPosition.azimuth() + "°"), new Paragraph("Elevation: " + sunPosition.elevation() + "°"), new Paragraph("Sunrise: " + sunPosition.sunrise()), new Paragraph("Sunset: " + sunPosition.sunset()));
    }

    public void showMoon(MoonPosition moonPosition) {
        summary.setText("Moon data updated.");
        content.removeAll();
        content.add(new Paragraph("Azimuth: " + moonPosition.azimuth() + "°"), new Paragraph("Elevation: " + moonPosition.elevation() + "°"), new Paragraph("Illumination: " + moonPosition.illumination() + "%"));
    }

    public void showClouds(String cloudSummary) {
        summary.setText("Cloud data updated.");
        content.removeAll();
        content.add(new Paragraph(cloudSummary));
    }
}
