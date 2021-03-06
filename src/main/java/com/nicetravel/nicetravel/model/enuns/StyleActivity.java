package com.nicetravel.nicetravel.model.enuns;

import org.springframework.lang.NonNull;

public enum StyleActivity {

    RESTAURANT( "Restaurant"),
    BAR( "Bar"),
    MUSEUM("Museum"),
    SHOP( "Shopping"),
    HISTORICAL_MONUMENT( "Historical monument"),
    SWIMMING( "Swimming"),
    PARK( "Park"),
    CHURCH( "Church"),
    SPORT( "Sport"),
    OTHER( "Other");

    public static final String CLASS_NAME = "com.nicetravel.nicetravel.model.enuns.StyleActivity";

    private final String description;

    StyleActivity(String description) {
        this.description = description;
    }

    public static StyleActivity valueOfEnum(@NonNull String description) {
        for (StyleActivity type : StyleActivity.values()) {
            if (description.trim().equalsIgnoreCase(type.getDescription())) {
                return type;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

}
