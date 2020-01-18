package com.nicetravel.nicetravel.service.scheduleday.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ActivityEntity;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MockFindScheduleDayService extends AbstractFindScheduleDayService {

    @Override
    public List<ScheduleDayDTO> getScheduleDays(Long scheduleId) {
        List<ScheduleDayDTO> scheduleDayDTOS = new ArrayList<>();
        for (int i = 1; i <= scheduleId; i++) {
            ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
            scheduleDayEntity.setDay(i);
            scheduleDayEntity.setCod((long) i);
            ActivityEntity activityEntity = new ActivityEntity();
            activityEntity.setPrice(BigDecimal.valueOf(new Random().nextInt(250) + 100.21));
            scheduleDayEntity.setActivities(Collections.singletonList(activityEntity));
            scheduleDayDTOS.add(new ScheduleDayDTO(scheduleDayEntity));
    }
        return scheduleDayDTOS;
    }


}
