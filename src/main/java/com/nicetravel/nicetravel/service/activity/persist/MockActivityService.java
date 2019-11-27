package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.dto.ActivityDTO;

import java.math.BigDecimal;
import java.time.LocalTime;

public class MockActivityService extends AbstractActivityService {

    @Override
    public ActivityDTO saveActivity(String description, String nameOfPlace, BigDecimal price, LocalTime startActivity,
                                    LocalTime finishActivity, String styleActivity, Long idScheduleDay, Long id) {
        return new ActivityDTO()
                .setDescription(description)
                .setNameOfPlace(nameOfPlace)
                .setPrice(price)
                .setStyleActivity(styleActivity)
                .setStartActivity(startActivity)
                .setFinishActivity(finishActivity)
                .setId(id)
                .setIdScheduleDay(idScheduleDay);
    }

    @Override
    public void delete(Long activityId) {
        //NOOP
    }
}
