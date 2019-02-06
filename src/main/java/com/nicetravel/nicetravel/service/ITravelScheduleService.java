package com.nicetravel.nicetravel.service;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.enuns.StyleTravel;

public interface ITravelScheduleService {

    ScheduleDTO generateTravelSchedule(String cityName, int numberOfDays, StyleTravel styleTravel);
}
