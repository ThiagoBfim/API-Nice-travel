package com.nicetravel.nicetravel.dto;

import java.util.ArrayList;
import java.util.List;

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

    public int getDay() {
        return day;
    }

    public ScheduleDayDTO setDay(int day) {
        this.day = day;
        return this;
    }
}
