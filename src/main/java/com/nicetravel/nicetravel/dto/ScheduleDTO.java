package com.nicetravel.nicetravel.dto;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDTO {

    private int qtdDays;
    private List<ScheduleDayDTO> scheduleDay = new ArrayList<>();
    private String imageUrl;
    private String nameCity;
    private Long scheduleCod;

    public int getQtdDays() {
        return qtdDays;
    }

    public ScheduleDTO setQtdDays(int qtdDays) {
        this.qtdDays = qtdDays;
        return this;
    }

    public List<ScheduleDayDTO> getScheduleDay() {
        return scheduleDay;
    }

    public ScheduleDTO setScheduleDay(List<ScheduleDayDTO> scheduleDay) {
        this.scheduleDay = scheduleDay;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BigDecimal getPriceFinal() {
        if(CollectionUtils.isEmpty(getScheduleDay())){
            return BigDecimal.ZERO;
        }
        return getScheduleDay()
                .stream()
                .filter(s -> s.getPriceDay() != null)
                .map(ScheduleDayDTO::getPriceDay)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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
