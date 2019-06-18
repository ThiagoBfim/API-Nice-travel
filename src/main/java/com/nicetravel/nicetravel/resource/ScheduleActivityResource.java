package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.dto.ActivityDTO;
import com.nicetravel.nicetravel.model.ActivityEntity;
import com.nicetravel.nicetravel.service.activity.persist.AbstractActivityService;
import com.nicetravel.nicetravel.service.activity.retrieve.AbstractFindActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ScheduleActivityResource {

    @Autowired
    AbstractFindActivityService abstractFindActivityService;

    @Autowired
    AbstractActivityService abstractActivityService;

    @GetMapping
    public List<ActivityDTO> getActivities(){
        return abstractFindActivityService.getActivities();
    }

    @PostMapping
    public ActivityEntity  addActivity(@Valid @RequestBody ActivityEntity activityEntity){
        return abstractActivityService.saveActivityOnDatabase(activityEntity);
    }

}
