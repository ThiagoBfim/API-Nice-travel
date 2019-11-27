package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.dto.ActivityDTO;
import org.springframework.lang.Nullable;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalTime;

public abstract class AbstractActivityService {

    @Transactional
    public abstract ActivityDTO saveActivity(String description,
                                             String nameOfPlace,
                                             BigDecimal price,
                                             LocalTime startActivity,
                                             LocalTime finishActivity,
                                             String styleActivity,
                                             Long idScheduleDay,
                                             @Nullable Long id);

    @Transactional
    public abstract void delete(Long activityId);
}
