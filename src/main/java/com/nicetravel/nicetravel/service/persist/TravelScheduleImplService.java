package com.nicetravel.nicetravel.service.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;

public class TravelScheduleImplService extends AbstractTravelScheduleService {
    @Override
    protected ScheduleTravelEntity saveScheduleTravelOnDatabase(String cityName, int styleTravel) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }

    @Override
    protected ScheduleDTO createSchedule(ScheduleTravelEntity scheduleTravelEntity) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }
}
