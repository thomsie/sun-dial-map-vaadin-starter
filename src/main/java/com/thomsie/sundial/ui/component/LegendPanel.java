package com.thomsie.sundial.ui.component;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LegendPanel extends VerticalLayout {
    public LegendPanel() {
        setPadding(false);
        setSpacing(false);
        add(new H3("Cloud legend"));
        add(legendRow("Low", "#7cc576"));
        add(legendRow("Mid", "#5dade2"));
        add(legendRow("High", "#d5d8dc"));
        add(legendRow("Fog", "#c39b77"));
    }

    private HorizontalLayout legendRow(String label, String color) {
        Div swatch = new Div();
        swatch.setWidth("16px");
        swatch.setHeight("16px");
        swatch.getStyle().set("background", color);
        swatch.getStyle().set("border-radius", "4px");
        HorizontalLayout row = new HorizontalLayout(swatch, new Span(label));
        row.setSpacing(true);
        row.setPadding(false);
        row.setAlignItems(Alignment.CENTER);
        return row;
    }
}
