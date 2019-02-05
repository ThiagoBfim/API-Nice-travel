package com.nicetravel.nicetravel.model.enuns;

public enum StyleTravel {

    ECONOMIC("E", "Económico"),
    LUX("L", "Luxo"),
    MODERATE("M", "Moderado");

    public static final String CLASS_NAME = "com.nicetravel.nicetravel.model.enuns.StyleTravel";

    private final String description;
    private final String abbreviation;

    StyleTravel(String abbreviation, String description) {
        this.abbreviation = abbreviation;
        this.description = description;
    }

    public static StyleTravel valueOfEnum(String abbreviation) {
        for (StyleTravel type : StyleTravel.values()) {
            if (abbreviation.trim().equals(type.getAbbreviation())) {
                return type;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
