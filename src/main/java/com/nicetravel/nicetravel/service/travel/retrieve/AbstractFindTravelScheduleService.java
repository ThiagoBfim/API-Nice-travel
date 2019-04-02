package com.nicetravel.nicetravel.service.travel.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;

import java.util.List;

public abstract class AbstractFindTravelScheduleService {

    public abstract List<ScheduleDTO> getScheduleByCityName(String cityName, Integer sizeElements);

    public abstract List<ScheduleDayDTO> getScheduleDays(Long scheduleId);

    public abstract List<ScheduleDTO> retrieveTravelSchedule(List<Long> scheduleIds);
}
