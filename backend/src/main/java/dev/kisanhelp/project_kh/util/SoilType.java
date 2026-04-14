package dev.kisanhelp.project_kh.util;

import dev.kisanhelp.project_kh.exception.InvalidSoilTypeException;

public enum SoilType {
    red, black, silt, sandy, loam, clay;

    public static SoilType fromString(String value) {
        for (SoilType type : SoilType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new InvalidSoilTypeException("Soil type '" + value + "' is not supported.");
    }
}