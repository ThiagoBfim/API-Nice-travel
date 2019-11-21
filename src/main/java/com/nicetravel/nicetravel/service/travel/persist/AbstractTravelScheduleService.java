package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;

import javax.transaction.Transactional;

/**
 * This class use Template Method pattern
 */
public abstract class AbstractTravelScheduleService {

    public ScheduleDTO generateTravelSchedule(String placeID, int numberDays) {
        CityEntity cityEntity = saveCity(placeID);
        ScheduleTravelEntity scheduleTravelEntity = saveScheduleTravel(cityEntity, numberDays);
        return createScheduleDTO(scheduleTravelEntity);
    }

    /**
     * This method have to save the  {@link ScheduleTravelEntity}.
     *
     * @param cityEntity
     * @param numberDays
     * @return ScheduleTravelEntity
     */
    @Transactional
    protected abstract ScheduleTravelEntity saveScheduleTravel(CityEntity cityEntity, int numberDays);

    /**
     * This method have to find city in Google API {@link com.nicetravel.nicetravel.service.external.GoogleMapsAPI} and save the City.
     * <p>
     * Note: This method have to verify if the city not exits
     *
     * @param placeID {@link com.nicetravel.nicetravel.service.external.GoogleMapsAPI}
     * @return CityEntity
     */
    @Transactional
    protected abstract CityEntity saveCity(String placeID);

    protected abstract ScheduleDTO createScheduleDTO(ScheduleTravelEntity scheduleTravelEntity);

    @Transactional
    public abstract boolean publishTravelSchedule(Long scheduleId);

    @Transactional
    public abstract boolean voteTravelSchedule(Long scheduleId, Boolean positiveVote);
}
