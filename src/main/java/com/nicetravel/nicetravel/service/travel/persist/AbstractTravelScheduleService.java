package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.model.UserEntity;

import javax.transaction.Transactional;

/**
 * This class use Template Method pattern
 */
public abstract class AbstractTravelScheduleService {

    public ScheduleDTO generateTravelSchedule(String placeID, int numberDays, String userUID, String userEmail, String userName) {
        CityEntity cityEntity = saveCity(placeID);
        UserEntity userOwner = createOrGetUser(userUID, userEmail, userName);
        ScheduleTravelEntity scheduleTravelEntity = saveScheduleTravel(cityEntity, numberDays, userOwner);
        return createScheduleDTO(scheduleTravelEntity);
    }

    protected abstract UserEntity createOrGetUser(String userUID, String userEmail, String userName);

    /**
     * This method have to save the  {@link ScheduleTravelEntity}.
     *
     * @param cityEntity
     * @param numberDays
     * @param userOwner
     * @return ScheduleTravelEntity
     */
    @Transactional
    protected abstract ScheduleTravelEntity saveScheduleTravel(CityEntity cityEntity, int numberDays, UserEntity userOwner);

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
