package com.nicetravel.nicetravel.service;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.enuns.StyleTravel;

public class TravelScheduleImplService extends ITravelScheduleService {

    @Override
    protected ScheduleDTO createSchedule(String cityName, int numberOfDays, StyleTravel styleTravel) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }

    @Override
    protected void saveTravelOnDatabase(String cityName, StyleTravel styleTravel) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }
}
