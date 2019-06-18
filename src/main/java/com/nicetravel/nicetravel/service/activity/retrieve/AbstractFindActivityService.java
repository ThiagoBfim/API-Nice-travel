package com.nicetravel.nicetravel.service.activity.retrieve;

import com.nicetravel.nicetravel.dto.ActivityDTO;

import java.util.List;

public abstract class AbstractFindActivityService {

    public abstract List<ActivityDTO> getActivities();

}
