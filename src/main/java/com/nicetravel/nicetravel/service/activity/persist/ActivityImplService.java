package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.dto.ActivityDTO;
import com.nicetravel.nicetravel.model.ActivityEntity;
import com.nicetravel.nicetravel.model.enuns.StyleActivity;
import com.nicetravel.nicetravel.repository.ActivityRepository;
import com.nicetravel.nicetravel.repository.ScheduleDayRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ActivityImplService extends AbstractActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    @Override
    public ActivityDTO saveActivity(ActivityDTO activityDTO) {
        ActivityEntity activityEntity;
        if (activityDTO.getId() != null) {
            activityEntity = activityRepository.getOne(activityDTO.getId());
        } else {
            activityEntity = new ActivityEntity();
        }
        activityEntity.setName(activityDTO.getNameOfPlace());
        activityEntity.setStyleActivity(StyleActivity.valueOfEnum(activityDTO.getStyleActivity()));
        activityEntity.setPrice(activityDTO.getPrice());
        activityEntity.setDtStart(activityDTO.getStartActivity());
        activityEntity.setDtEnd(activityDTO.getFinishActivity());
        activityEntity.setDescription(activityDTO.getDescription());
        activityEntity.setScheduleDayEntity(scheduleDayRepository.getOne(activityDTO.getIdScheduleDay()));
        ActivityEntity activitySaved = activityRepository.save(activityEntity);
        activityDTO.setId(activitySaved.getCod());
        return activityDTO;
    }

    @Override
    public void delete(Long activityId) {
        activityRepository.deleteById(activityId);
    }
}
