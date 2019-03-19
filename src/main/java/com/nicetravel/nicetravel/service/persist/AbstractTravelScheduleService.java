package com.nicetravel.nicetravel.service.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;

import javax.transaction.Transactional;

/**
 * This class use Template Method pattern
 */
public abstract class AbstractTravelScheduleService {

    public ScheduleDTO generateTravelSchedule(String cityName, int numberDays) {
        CityEntity cityEntity = saveCityOnDatabase(cityName);
        ScheduleTravelEntity scheduleTravelEntity = saveScheduleTravelOnDatabase(cityEntity, numberDays);
        return createSchedule(scheduleTravelEntity);
    }

    /**
     * This method have to save the  {@link ScheduleTravelEntity} and {@link com.nicetravel.nicetravel.model.CityEntity} if necessery.
     * <p>
     * Note: This method have to save new Schedule Travel
     *
     * @param cityEntity
     * @param styleTravel
     * @return ScheduleTravelEntity
     */
    @Transactional
    protected abstract ScheduleTravelEntity saveScheduleTravelOnDatabase(CityEntity cityEntity, int styleTravel);

    /**
     * This method have to find city int Google API {@link com.nicetravel.nicetravel.service.external.GoogleMapsAPI} and save the City.
     * <p>
     * Note: This method have to verify if the city not exits
     *
     * @param cityName
     * @return CityEntity
     */
    @Transactional
    protected abstract CityEntity saveCityOnDatabase(String cityName);


    protected abstract ScheduleDTO createSchedule(ScheduleTravelEntity scheduleTravelEntity);


}
