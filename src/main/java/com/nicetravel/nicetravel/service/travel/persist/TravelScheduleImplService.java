package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;

public class TravelScheduleImplService extends AbstractTravelScheduleService {

    @Override
    protected ScheduleTravelEntity saveScheduleTravelOnDatabase(CityEntity cityEntity, int styleTravel) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }

    @Override
    protected CityEntity saveCityOnDatabase(String cityName) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }

    @Override
    protected ScheduleDTO createSchedule(ScheduleTravelEntity scheduleTravelEntity) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }
}
