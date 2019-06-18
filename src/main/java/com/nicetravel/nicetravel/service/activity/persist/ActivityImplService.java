package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.model.ActivityEntity;
import com.nicetravel.nicetravel.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class ActivityImplService extends AbstractActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public ActivityEntity saveActivityOnDatabase(ActivityEntity activityEntity) {
        return activityRepository.save(activityEntity);
    }
}
