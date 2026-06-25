package com.thomsie.sundial.ui.component;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;

@Tag("map-panel")
@JsModule("./leaflet-map.ts")
@NpmPackage(value = "leaflet", version = "^1.9.4")
@NpmPackage(value = "@types/leaflet", version = "^1.9.12")
@CssImport("./styles/map.css")
public class MapPanel extends Composite<Div> {
    public MapPanel() {
        getContent().setId("map-host");
        getContent().setHeight("500px");
        getContent().setWidthFull();
        getContent().setHeight("100%");
        getContent().getStyle().set("min-height", "600px");
        getContent().getStyle().set("background", "var(--lumo-contrast-5pct)");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        // Sobald die Komponente im Browser sitzt, rufen wir initMap auf
        getContent().getElement().executeJs("window.sunDialMap?.initMap($0)", getContent().getId().orElse("map-host"));
    }

    public void setCoordinates(double latitude, double longitude) {
        getContent().getElement().executeJs("window.sunDialMap?.setMarker($0, $1)", latitude, longitude);
        getContent().getElement().executeJs("window.sunDialMap?.setView($0, $1, 7)", latitude, longitude);
    }
}
