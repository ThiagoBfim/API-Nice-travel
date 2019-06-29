package com.nicetravel.nicetravel.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScheduleDayDTO {

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
        return priceDay.setScale(2, RoundingMode.HALF_EVEN);
    }

    public ScheduleDayDTO setPriceDay(BigDecimal priceDay) {
         this.priceDay = priceDay;
         return this;
    }

}
