package com.nicetravel.nicetravel.dto;

import com.nicetravel.nicetravel.model.enuns.StyleTravel;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDTO {

    private int totalDays;
    private List<ScheduleDayDTO> activities = new ArrayList<>();
    private StyleTravel styleTravel;

    public int getTotalDays() {
        return totalDays;
    }

    public ScheduleDTO setTotalDays(int totalDays) {
        this.totalDays = totalDays;
        return this;
    }

    public List<ScheduleDayDTO> getActivities() {
        return activities;
    }

    public ScheduleDTO setActivities(List<ScheduleDayDTO> activities) {
        this.activities = activities;
        return this;
    }

    public StyleTravel getStyleTravel() {
        return styleTravel;
    }

    public ScheduleDTO setStyleTravel(StyleTravel styleTravel) {
        this.styleTravel = styleTravel;
        return this;
    }
}
