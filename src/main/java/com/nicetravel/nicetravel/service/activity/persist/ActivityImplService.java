package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.dto.ActivityDTO;
import com.nicetravel.nicetravel.model.ActivityEntity;
import com.nicetravel.nicetravel.model.enuns.StyleActivity;
import com.nicetravel.nicetravel.repository.ActivityRepository;
import com.nicetravel.nicetravel.repository.ScheduleDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalTime;


public class ActivityImplService extends AbstractActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    @Override
    public ActivityDTO saveActivity(String description, String nameOfPlace, BigDecimal price, LocalTime startActivity,
                                    LocalTime finishActivity, String styleActivity, Long idScheduleDay, @Nullable Long id) {

        ActivityEntity activityEntity;
        if (id != null) {
            activityEntity = activityRepository.getOne(id);
        } else {
            activityEntity = new ActivityEntity();
        }
        activityEntity.setName(nameOfPlace);
        activityEntity.setStyleActivity(StyleActivity.valueOfEnum(styleActivity));
        activityEntity.setPrice(price);
        activityEntity.setDtStart(startActivity);
        activityEntity.setDtEnd(finishActivity);
        activityEntity.setDescription(description);
        activityEntity.setScheduleDayEntity(scheduleDayRepository.getOne(idScheduleDay));
        ActivityEntity activitySaved = activityRepository.save(activityEntity);
        return new ActivityDTO(activitySaved);
    }

    @Override
    public void delete(Long activityId) {
        activityRepository.deleteById(activityId);
    }
}
