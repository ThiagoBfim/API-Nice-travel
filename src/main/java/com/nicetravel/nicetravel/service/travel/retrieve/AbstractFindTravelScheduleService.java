package com.nicetravel.nicetravel.service.travel.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import org.springframework.lang.NonNull;

import java.util.List;

public abstract class AbstractFindTravelScheduleService {

    public abstract List<ScheduleDTO> getScheduleByCityName(@NonNull String cityName, @NonNull Integer sizeElements);

    public abstract List<ScheduleDTO> retrieveTravelSchedule(@NonNull List<Long> scheduleIds);

    public abstract List<ScheduleDTO> retrieveTravelScheduleByUserUID(String userUID);
}
