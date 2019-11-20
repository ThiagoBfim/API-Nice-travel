package com.nicetravel.nicetravel.service.scheduleday.persist;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;

import javax.transaction.Transactional;

public abstract class AbstractScheduleDayService {

    @Transactional
    public abstract void removeById(Long scheduleDayId);

    public ScheduleDayDTO addScheduleDay(Long scheduleId){
        ScheduleDayEntity scheduleDayEntity = saveScheduleDay(scheduleId);
        return createScheduleDayDTO(scheduleDayEntity);
    }

    @Transactional
    public abstract ScheduleDayEntity saveScheduleDay(Long scheduleId);

    protected abstract ScheduleDayDTO createScheduleDayDTO(ScheduleDayEntity scheduleDayEntity);

}
