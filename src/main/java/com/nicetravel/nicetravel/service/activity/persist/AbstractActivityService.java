package com.nicetravel.nicetravel.service.activity.persist;

import com.nicetravel.nicetravel.dto.ActivityDTO;

import javax.transaction.Transactional;

public abstract class AbstractActivityService {

    @Transactional
    public abstract ActivityDTO saveActivityOnDatabase(ActivityDTO activityDTO);

}
