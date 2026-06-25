package com.thomsie.sundial.ui.component;

import com.thomsie.sundial.domain.CloudLayerType;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumSet;

public class ControlPanel extends VerticalLayout {
    private final NumberField latitudeField = new NumberField("Latitude");
    private final NumberField longitudeField = new NumberField("Longitude");
    private final DateTimePicker dateTimePicker = new DateTimePicker("Date and time");
    private final RadioButtonGroup<String> modeGroup = new RadioButtonGroup<>();
    private final CheckboxGroup<CloudLayerType> cloudLayerGroup = new CheckboxGroup<>();
    private final Button calculateButton = new Button("Calculate");

    public ControlPanel() {
        setSpacing(true);
        setPadding(false);
        setWidth("360px");
        H3 title = new H3("Controls");
        latitudeField.setValue(47.3769);
        latitudeField.setStep(0.0001);
        latitudeField.setMin(-90);
        latitudeField.setMax(90);
        longitudeField.setValue(8.5417);
        longitudeField.setStep(0.0001);
        longitudeField.setMin(-180);
        longitudeField.setMax(180);
        dateTimePicker.setValue(LocalDateTime.now());
        dateTimePicker.setStep(Duration.ofMinutes(15));
        modeGroup.setLabel("Mode");
        modeGroup.setItems("Sun", "Moon", "Clouds");
        modeGroup.setValue("Sun");
        cloudLayerGroup.setLabel("Cloud layers");
        cloudLayerGroup.setItems(CloudLayerType.values());
        cloudLayerGroup.setItemLabelGenerator(CloudLayerType::getLabel);
        cloudLayerGroup.setValue(EnumSet.of(CloudLayerType.LOW, CloudLayerType.MID, CloudLayerType.HIGH));
        FormLayout formLayout = new FormLayout();
        formLayout.add(latitudeField, longitudeField, dateTimePicker);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        Section section = new Section(title, formLayout, modeGroup, cloudLayerGroup, calculateButton);
        section.getStyle().set("padding", "1rem");
        section.getStyle().set("border-radius", "var(--lumo-border-radius-l)");
        section.getStyle().set("background", "var(--lumo-base-color)");
        section.getStyle().set("box-shadow", "var(--lumo-box-shadow-xs)");
        section.setWidthFull();
        add(section);
    }

    public NumberField getLatitudeField() { return latitudeField; }
    public NumberField getLongitudeField() { return longitudeField; }
    public DateTimePicker getDateTimePicker() { return dateTimePicker; }
    public RadioButtonGroup<String> getModeGroup() { return modeGroup; }
    public CheckboxGroup<CloudLayerType> getCloudLayerGroup() { return cloudLayerGroup; }
    public Button getCalculateButton() { return calculateButton; }
}
