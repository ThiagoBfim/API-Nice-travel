package com.nicetravel.nicetravel.dto;

import java.math.BigDecimal;

public class ScheduleDayDTO {

    private Long id;
    private int day;
    private BigDecimal priceDay;

    public int getDay() {
        return day;
    }

    public ScheduleDayDTO setDay(int day) {
        this.day = day;
        return this;
    }

    public BigDecimal getPriceDay() {
        return priceDay;
    }

    public ScheduleDayDTO setPriceDay(BigDecimal priceDay) {
         this.priceDay = priceDay;
         return this;
    }

    public Long getId() {
        return id;
    }

    public ScheduleDayDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
