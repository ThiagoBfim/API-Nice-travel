package com.nicetravel.nicetravel.dto;

import com.nicetravel.nicetravel.model.ActivityEntity;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleDayDTO {

    private List<ActivityDTO> activities = new ArrayList<>();
    private int day;

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public ScheduleDayDTO setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
        return this;
    }

    public ScheduleDayDTO setActivitiesByActivityEntity(List<ActivityEntity> activitiesEntity) {
        activities = activitiesEntity.stream()
                .map(a -> new ActivityDTO()
                        .setNameOfPlace(a.getName())
                        .setDescription(a.getDescription())
                        .setStartActivity(a.getDtStart())
                        .setFinishActivity(a.getDtEnd())
                        .setPrice(a.getPrice())
                        .setStyleActivity(a.getStyleActivity().getDescription()))
                .collect(Collectors.toList());
        return this;
    }

    public int getDay() {
        return day;
    }

    public ScheduleDayDTO setDay(int day) {
        this.day = day;
        return this;
    }

    public BigDecimal getPriceDay() {
        if (CollectionUtils.isEmpty(getActivities())) {
            return BigDecimal.ZERO;
        }
        return getActivities().stream()
                .filter(a -> a.getPrice() != null)
                .map(ActivityDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
