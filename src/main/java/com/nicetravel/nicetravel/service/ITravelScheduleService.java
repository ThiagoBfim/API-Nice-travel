package com.nicetravel.nicetravel.service;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.enuns.StyleTravel;

/**
 * This class use Template Method pattern
 */
public abstract class ITravelScheduleService {

    public ScheduleDTO generateTravelSchedule(String cityName, int numberOfDays, StyleTravel styleTravel) {
        saveTravelOnDatabase(cityName, styleTravel);
        return createSchedule(cityName, numberOfDays, styleTravel);
    }

    protected abstract ScheduleDTO createSchedule(String cityName, int numberOfDays, StyleTravel styleTravel);

    /**
     * This method have to save the Travel and the City.
     * This database will be used to predict new travel's.
     * <p>
     * Note: This method should consult some API for know the mean price of hotel for the Style Travel,
     * and the type of the city.
     *
     * @param cityName    The city name.
     * @param styleTravel The style of the travel.
     */
    protected abstract void saveTravelOnDatabase(String cityName, StyleTravel styleTravel);

}
