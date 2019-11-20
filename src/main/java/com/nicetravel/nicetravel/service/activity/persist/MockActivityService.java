package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.dto.ActivityDTO;

public class MockActivityService extends AbstractActivityService {

    @Override
    public ActivityDTO saveActivityOnDatabase(ActivityDTO activityDTO) {
        return activityDTO;
    }
}
