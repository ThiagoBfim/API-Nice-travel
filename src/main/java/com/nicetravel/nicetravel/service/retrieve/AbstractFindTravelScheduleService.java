package com.nicetravel.nicetravel.service.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDTO;

import java.util.List;

public abstract class AbstractFindTravelScheduleService {

    public abstract List<ScheduleDTO> getScheduleByCityName(String cityName, Integer sizeElements);
}
