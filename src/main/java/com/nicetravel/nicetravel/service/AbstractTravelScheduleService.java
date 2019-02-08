package com.nicetravel.nicetravel.service;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.enuns.StyleTravel;

/**
 * This class use Template Method pattern
 */
public abstract class AbstractTravelScheduleService {

    public ScheduleDTO generateTravelSchedule(String cityName, int numberOfDays, StyleTravel styleTravel) {
        saveCityOnDatabase(cityName, styleTravel);
        return createSchedule(cityName, numberOfDays, styleTravel);
    }

    /**
     * This method have to save the City.
     *
     * Note: This method have to verify if the city not exits
     * @param cityName
     * @param styleTravel
     */
    protected abstract void saveCityOnDatabase(String cityName, StyleTravel styleTravel);

    protected abstract ScheduleDTO createSchedule(String cityName, int numberOfDays, StyleTravel styleTravel);


}
