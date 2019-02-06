package com.nicetravel.nicetravel.dto;

import java.math.BigDecimal;
import java.time.LocalTime;

public class ActivityDTO {

    private String description;
    private String nameOfPlace;
    private BigDecimal price = BigDecimal.ZERO;
    private LocalTime startActivity;
    private LocalTime finishActivity;
    //private byte[] photo;


    public String getDescription() {
        return description;
    }

    public ActivityDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getNameOfPlace() {
        return nameOfPlace;
    }

    public ActivityDTO setNameOfPlace(String nameOfPlace) {
        this.nameOfPlace = nameOfPlace;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ActivityDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalTime getStartActivity() {
        return startActivity;
    }

    public ActivityDTO setStartActivity(LocalTime startActivity) {
        this.startActivity = startActivity;
        return this;
    }

    public LocalTime getFinishActivity() {
        return finishActivity;
    }

    public ActivityDTO setFinishActivity(LocalTime finishActivity) {
        this.finishActivity = finishActivity;
        return this;
    }
}
