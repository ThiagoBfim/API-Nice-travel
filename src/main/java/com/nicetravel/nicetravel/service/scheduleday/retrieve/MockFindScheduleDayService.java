package com.nicetravel.nicetravel.service.scheduleday.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockFindScheduleDayService extends AbstractFindScheduleDayService {

    @Override
    public List<ScheduleDayDTO> getScheduleDays(Long scheduleId) {
        List<ScheduleDayDTO> scheduleDayDTOS = new ArrayList<>();
        for (int i = 1; i <= scheduleId; i++) {
            scheduleDayDTOS.add(new ScheduleDayDTO()
                    .setId((long) i)
                    .setDay(i)
                    .setPriceDay(new BigDecimal(new Random().nextInt(250) + 100.21)));
        }
        return scheduleDayDTOS;
    }


}
