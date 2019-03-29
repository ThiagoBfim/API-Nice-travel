package com.nicetravel.nicetravel.service.travel.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;

import java.util.List;

public class FindTravelScheduleImplService extends AbstractFindTravelScheduleService {

    @Override
    public List<ScheduleDTO> getScheduleByCityName(String cityName, Integer sizeElements) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }

    @Override
    public List<ScheduleDayDTO> getScheduleDays(Long scheduleId) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }
}
