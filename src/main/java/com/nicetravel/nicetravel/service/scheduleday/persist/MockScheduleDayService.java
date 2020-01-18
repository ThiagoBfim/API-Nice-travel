package com.nicetravel.nicetravel.service.scheduleday.persist;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;

public class MockScheduleDayService extends AbstractScheduleDayService {

    @Override
    public void deleteById(Long scheduleDayId) {
        //NOOP
    }

    @Override
    public ScheduleDayEntity saveScheduleDay(Long scheduleId) {
        ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
        scheduleDayEntity.setDay(10);
        scheduleDayEntity.setCod(10L);
        return scheduleDayEntity;
    }


    @Override
    protected ScheduleDayDTO createScheduleDayDTO(ScheduleDayEntity scheduleDayEntity) {
        return new ScheduleDayDTO(scheduleDayEntity);
    }

    @Override
    public void reorder(Long scheduleDayIdFrom, Long scheduleDayIdTo) {
        //NOOP
    }
}
