package com.nicetravel.nicetravel.service.external;

import java.io.Serializable;
import java.util.List;

public class PlaceDTO implements Serializable {
    
    private Double lat;
    private Double lng;
    private String name;
    private List<String> imageUrl;
    private String types;
    private String formattedAddress;

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

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public PlaceDTO setImageUrl(List<String> imageUrl) {
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

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public PlaceDTO setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }
}
