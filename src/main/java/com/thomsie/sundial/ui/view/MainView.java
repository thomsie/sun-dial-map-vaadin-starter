package com.thomsie.sundial.ui.view;

import com.thomsie.sundial.domain.Coordinates;
import com.thomsie.sundial.service.AstronomyService;
import com.thomsie.sundial.service.WeatherService;
import com.thomsie.sundial.ui.component.ControlPanel;
import com.thomsie.sundial.ui.component.InfoPanel;
import com.thomsie.sundial.ui.component.LayerPanel;
import com.thomsie.sundial.ui.component.LegendPanel;
import com.thomsie.sundial.ui.component.MapPanel;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;

@PageTitle("Sun Dial Map")
@Route("")
public class MainView extends AppLayout {

    public MainView(AstronomyService astronomyService, WeatherService weatherService) {
        H2 header = new H2("Sun Dial Map");
        header.getStyle().set("margin", "0");
        addToNavbar(header);

        MapPanel mapPanel = new MapPanel();
        ControlPanel controlPanel = new ControlPanel();
        InfoPanel infoPanel = new InfoPanel();
        LayerPanel layerPanel = new LayerPanel();
        LegendPanel legendPanel = new LegendPanel();

        VerticalLayout sidebar = new VerticalLayout(controlPanel, infoPanel, layerPanel, legendPanel);
        sidebar.setPadding(true);
        sidebar.setSpacing(true);
        sidebar.setWidthFull();

        Scroller scroller = new Scroller();
        scroller.setContent(sidebar);
        scroller.setSizeFull();

        Div mapContainer = new Div(mapPanel);
        mapContainer.setSizeFull();

        SplitLayout splitLayout = new SplitLayout(mapContainer, scroller);
        splitLayout.setHeight("100%"        );
        splitLayout.setSizeFull();
        splitLayout.setSplitterPosition(72);

        setContent(splitLayout);

        controlPanel.getCalculateButton().addClickListener(event -> {
            Double latitude = controlPanel.getLatitudeField().getValue();
            Double longitude = controlPanel.getLongitudeField().getValue();
            LocalDateTime dateTime = controlPanel.getDateTimePicker().getValue();
            String mode = controlPanel.getModeGroup().getValue();

            if (latitude == null || longitude == null || dateTime == null) {
                Notification.show("Please provide latitude, longitude, and date/time.");
                return;
            }

            Coordinates coordinates = new Coordinates(latitude, longitude);
            mapPanel.setCoordinates(latitude, longitude);

            switch (mode) {
                case "Moon" -> infoPanel.showMoon(astronomyService.calculateMoonPosition(coordinates, dateTime));
                case "Clouds" -> infoPanel.showClouds(weatherService.getCloudSummary(coordinates));
                case "Sun" -> infoPanel.showSun(astronomyService.calculateSunPosition(coordinates, dateTime));
                default -> Notification.show("Unsupported mode: " + mode);
            }
        });
    }
}
