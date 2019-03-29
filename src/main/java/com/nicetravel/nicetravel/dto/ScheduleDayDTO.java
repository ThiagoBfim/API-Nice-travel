package com.nicetravel.nicetravel.dto;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

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
        return priceDay;
    }


    public ScheduleDayDTO setPriceDay(List<ActivityDTO> activityDTOS) {
        if (CollectionUtils.isEmpty(activityDTOS)) {
            this.priceDay = BigDecimal.ZERO;
            return this;
        }
        this.priceDay = activityDTOS.stream()
                .filter(a -> a.getPrice() != null)
                .map(ActivityDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return this;
    }
}
