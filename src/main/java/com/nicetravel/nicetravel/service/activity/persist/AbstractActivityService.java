package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.model.ActivityEntity;

public abstract class AbstractActivityService {

    public abstract ActivityEntity saveActivityOnDatabase(ActivityEntity scheduleDayEntity);

}
