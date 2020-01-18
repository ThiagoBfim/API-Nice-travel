package com.nicetravel.nicetravel.dto;

import com.nicetravel.nicetravel.model.ScheduleDayEntity;

import java.math.BigDecimal;

public class ScheduleDayDTO {

    private Long id;
    private int day;
    private BigDecimal priceDay;
    private int qtdActivities;
    private String typeFirstActivity;

    public ScheduleDayDTO(ScheduleDayEntity scheduleDay) {
        this.id = scheduleDay.getCod();
        this.day = scheduleDay.getDay();
        this.priceDay = scheduleDay.getPriceDay();
        this.qtdActivities = scheduleDay.getQtdActivities();
        this.typeFirstActivity = scheduleDay.getFirstActivity();
    }

    public int getDay() {
        return day;
    }

    public BigDecimal getPriceDay() {
        return priceDay;
    }

    public Long getId() {
        return id;
    }

    public int getQtdActivities() {
        return qtdActivities;
    }

    public String getTypeFirstActivity() {
        return typeFirstActivity;
    }
}
