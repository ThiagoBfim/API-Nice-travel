package com.nicetravel.nicetravel.service.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;

import javax.transaction.Transactional;

/**
 * This class use Template Method pattern
 */
public abstract class AbstractTravelScheduleService {

    public ScheduleDTO generateTravelSchedule(String cityName, int numberDays) {
        ScheduleTravelEntity scheduleTravelEntity = saveScheduleTravelOnDatabase(cityName, numberDays);
        return createSchedule(scheduleTravelEntity);
    }

    /**
     * This method have to save the  {@link ScheduleTravelEntity} and {@link com.nicetravel.nicetravel.model.CityEntity} if necessery.
     * <p>
     * Note: This method have to save new Schedule Travel
     *
     * @param cityName
     * @param styleTravel
     */
    @Transactional
    protected abstract ScheduleTravelEntity saveScheduleTravelOnDatabase(String cityName, int styleTravel);

    protected abstract ScheduleDTO createSchedule(ScheduleTravelEntity scheduleTravelEntity);


}
