package com.nicetravel.nicetravel.dto;

import com.nicetravel.nicetravel.model.ActivityEntity;

import java.math.BigDecimal;
import java.time.LocalTime;

public class ActivityDTO {

    private String description;
    private String nameOfPlace;
    private BigDecimal price = BigDecimal.ZERO;
    private LocalTime startActivity;
    private LocalTime finishActivity;
    private String styleActivity;
    private Long idScheduleDay;
    private Long id;

    public ActivityDTO(ActivityEntity activityEntity) {
        setNameOfPlace(activityEntity.getName());
        setDescription(activityEntity.getDescription());
        setStartActivity(activityEntity.getDtStart());
        setFinishActivity(activityEntity.getDtEnd());
        setPrice(activityEntity.getPrice());
        setIdScheduleDay(activityEntity.getScheduleDayEntity().getCod());
        setStyleActivity(activityEntity.getStyleActivity().getDescription());
        setId(activityEntity.getCod());
    }

    public ActivityDTO() {
    }

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

    public String getStyleActivity() {
        return styleActivity;
    }

    public ActivityDTO setStyleActivity(String styleActivity) {
        this.styleActivity = styleActivity;
        return this;
    }

    public Long getIdScheduleDay() {
        return idScheduleDay;
    }

    public ActivityDTO setIdScheduleDay(Long idScheduleDay) {
        this.idScheduleDay = idScheduleDay;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ActivityDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
