package com.nicetravel.nicetravel.service.scheduleday.persist;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;

import java.math.BigDecimal;
import java.util.Random;

public class MockScheduleDayService extends AbstractScheduleDayService {

    @Override
    public void removeById(Long scheduleDayId) {
        //NOOP
    }

    @Override
    public ScheduleDayEntity saveScheduleDay(Long scheduleId) {
        return new ScheduleDayEntity();
    }


    @Override
    protected ScheduleDayDTO createScheduleDayDTO(ScheduleDayEntity scheduleDayEntity) {
        return  new ScheduleDayDTO()
                .setId(scheduleDayEntity.getCod())
                .setDay(scheduleDayEntity.getDay())
                .setPriceDay(new BigDecimal(new Random().nextInt(250) + 100.21));
    }
}
