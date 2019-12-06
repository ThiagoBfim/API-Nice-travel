package com.nicetravel.nicetravel.service.scheduleday.persist;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;

import java.math.BigDecimal;
import java.util.Random;

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
        return new ScheduleDayDTO()
                .setId(scheduleDayEntity.getCod())
                .setDay(scheduleDayEntity.getDay())
                .setPriceDay(new BigDecimal(new Random().nextInt(250) + 100.21));
    }

    @Override
    public void reorder(Long scheduleDayIdFrom, Long scheduleDayIdTo) {
        //NOOP
    }
}
