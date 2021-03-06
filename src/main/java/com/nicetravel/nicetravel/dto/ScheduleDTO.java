package com.nicetravel.nicetravel.dto;

import com.nicetravel.nicetravel.model.ScheduleTravelEntity;

import java.math.BigDecimal;

public class ScheduleDTO {

    private int qtdDays;
    private String imageUrl;
    private String cityAddress;
    private Long scheduleCod;
    private BigDecimal priceFinal;
    private String userUID;
    private String userName;
    private Integer numberStar;
    private Boolean publish;

    public ScheduleDTO() {
        //NOOP
    }

    public ScheduleDTO(ScheduleTravelEntity scheduleTravel) {
        setPriceFinal(scheduleTravel.getPriceFinal());
        setScheduleCod(scheduleTravel.getCod());
        setImageUrl(scheduleTravel.getCityImageUrl());
        setCityAddress(scheduleTravel.getFormattedAdress());
        setQtdDays(scheduleTravel.getScheduleDayEntities().size());
        setUserName(scheduleTravel.getUserOwner().getName());
        setUserUID(scheduleTravel.getUserOwner().getUid());
        setNumberStar(scheduleTravel.getNumberStar());
        setPublish(scheduleTravel.getPublicAccess());
    }

    public Boolean getPublish() {
        return publish;
    }

    public ScheduleDTO setPublish(Boolean publish) {
        this.publish = publish;
        return this;
    }

    public Integer getNumberStar() {
        return numberStar;
    }

    public ScheduleDTO setNumberStar(Integer numberStar) {
        this.numberStar = numberStar;
        return this;
    }

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

    public ScheduleDTO setPriceFinal(BigDecimal priceFinal) {
        this.priceFinal = priceFinal;
        return this;
    }

    public ScheduleDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public ScheduleDTO setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
        return this;
    }

    public Long getScheduleCod() {
        return scheduleCod;
    }

    public ScheduleDTO setScheduleCod(Long scheduleCod) {
        this.scheduleCod = scheduleCod;
        return this;
    }

    public String getUserUID() {
        return userUID;
    }

    public ScheduleDTO setUserUID(String userUID) {
        this.userUID = userUID;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ScheduleDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
