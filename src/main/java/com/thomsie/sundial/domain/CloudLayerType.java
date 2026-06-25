package com.thomsie.sundial.domain;

public enum CloudLayerType {
    LOW("Low clouds"),
    MID("Mid clouds"),
    HIGH("High clouds"),
    FOG("Fog");

    private final String label;

    CloudLayerType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
