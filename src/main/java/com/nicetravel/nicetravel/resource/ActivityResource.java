package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.dto.ActivityDTO;
import com.nicetravel.nicetravel.service.activity.persist.AbstractActivityService;
import com.nicetravel.nicetravel.service.activity.retrieve.AbstractFindActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityResource {

    @Autowired
    private AbstractFindActivityService abstractFindActivityService;

    @Autowired
    private AbstractActivityService abstractActivityService;

    @GetMapping
    public List<ActivityDTO> getActivities(@RequestParam("scheduleDayId") Long scheduleDayId) {
        ResourceUtil.validateValue(scheduleDayId, "scheduleDayId");
        return abstractFindActivityService.getActivities(scheduleDayId);
    }

    @PostMapping("/add")
    public ActivityDTO addActivity(@RequestParam("description") String description,
                                   @RequestParam("nameOfPlace") String nameOfPlace,
                                   @RequestParam("price") BigDecimal price,
                                   @RequestParam("startActivity") LocalTime startActivity,
                                   @RequestParam(value = "finishActivity", required = false) LocalTime finishActivity,
                                   @RequestParam("styleActivity") String styleActivity,
                                   @RequestParam("idScheduleDay") Long idScheduleDay,
                                   @RequestParam(value = "id", required = false) Long id) {
        ResourceUtil.validateValue(description, "description");
        ResourceUtil.validateValue(nameOfPlace, "nameOfPlace");
        ResourceUtil.validateValue(price, "price");
        ResourceUtil.validateValue(startActivity, "startActivity");
        ResourceUtil.validateValue(styleActivity, "styleActivity");
        ResourceUtil.validateValue(idScheduleDay, "idScheduleDay");
        return abstractActivityService.saveActivity(description,
                nameOfPlace,
                price,
                startActivity,
                finishActivity,
                styleActivity,
                idScheduleDay,
                id
        );
    }

    @DeleteMapping("/delete")
    public void deleteActivity(@RequestParam("activityId") Long activityId) {
        ResourceUtil.validateValue(activityId, "activityId");
        abstractActivityService.delete(activityId);
    }

}
