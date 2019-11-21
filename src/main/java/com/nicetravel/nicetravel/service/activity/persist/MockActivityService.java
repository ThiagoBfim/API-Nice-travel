package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.dto.ActivityDTO;

public class MockActivityService extends AbstractActivityService {

    @Override
    public ActivityDTO saveActivity(ActivityDTO activityDTO) {
        return activityDTO;
    }

    @Override
    public void delete(Long activityId) {
        //NOOP
    }
}
