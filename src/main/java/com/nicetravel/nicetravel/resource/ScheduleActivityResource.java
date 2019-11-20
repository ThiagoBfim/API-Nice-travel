package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.dto.ActivityDTO;
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
    private AbstractFindActivityService abstractFindActivityService;

    @Autowired
    private AbstractActivityService abstractActivityService;

    @GetMapping
    public List<ActivityDTO> getActivities(@RequestParam("scheduleDayId") Long scheduleDayId) {
        return abstractFindActivityService.getActivities(scheduleDayId);
    }

    @PostMapping
    public ActivityDTO addActivity(@Valid @RequestBody ActivityDTO activityDTO) {
        return abstractActivityService.saveActivityOnDatabase(activityDTO);
    }

}
