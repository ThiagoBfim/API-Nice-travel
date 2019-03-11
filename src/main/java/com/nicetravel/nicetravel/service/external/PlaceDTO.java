package com.nicetravel.nicetravel.service.external;

import java.io.Serializable;

public class PlaceDTO implements Serializable {
    
    private Double lat;
    private Double lng;
    private String name;
    private String imageUrl;
    private String types;

    public Double getLat() {
        return lat;
    }

    public PlaceDTO setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public PlaceDTO setLng(Double log) {
        this.lng = log;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlaceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PlaceDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getTypes() {
        return types;
    }

    public PlaceDTO setTypes(String types) {
        this.types = types;
        return this;
    }
}
