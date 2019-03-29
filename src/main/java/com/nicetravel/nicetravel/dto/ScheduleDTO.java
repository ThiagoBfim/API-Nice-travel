package com.nicetravel.nicetravel.dto;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

public class ScheduleDTO {

    private int qtdDays;
    private String imageUrl;
    private String nameCity;
    private Long scheduleCod;
    private BigDecimal priceFinal;

    public int getQtdDays() {
        return qtdDays;
    }

    public ScheduleDTO setQtdDays(int qtdDays) {
        this.qtdDays = qtdDays;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BigDecimal getPriceFinal() {
        return priceFinal;
    }

    public ScheduleDTO setPriceFinal(List<ScheduleDayDTO> scheduleDay) {
        if (CollectionUtils.isEmpty(scheduleDay)) {
            this.priceFinal = BigDecimal.ZERO;
            return this;
        }
        this.priceFinal = scheduleDay
                .stream()
                .filter(s -> s.getPriceDay() != null)
                .map(ScheduleDayDTO::getPriceDay)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return this;
    }

    public ScheduleDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getNameCity() {
        return nameCity;
    }

    public ScheduleDTO setNameCity(String nameCity) {
        this.nameCity = nameCity;
        return this;
    }

    public Long getScheduleCod() {
        return scheduleCod;
    }

    public ScheduleDTO setScheduleCod(Long scheduleCod) {
        this.scheduleCod = scheduleCod;
        return this;
    }
}
