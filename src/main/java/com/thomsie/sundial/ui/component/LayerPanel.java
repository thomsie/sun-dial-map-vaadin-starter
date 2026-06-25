package com.thomsie.sundial.ui.component;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LayerPanel extends VerticalLayout {
    public LayerPanel() {
        setPadding(false);
        setSpacing(false);
        add(new H3("Visible overlays"), new Checkbox("Sun direction", true), new Checkbox("Moon direction", false), new Checkbox("Cloud overlay", true));
    }
}
