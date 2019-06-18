package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.model.ActivityEntity;

public class MockActivityService extends AbstractActivityService {

    @Override
    public ActivityEntity saveActivityOnDatabase(ActivityEntity activityEntity) {
        return activityEntity;
    }
}
