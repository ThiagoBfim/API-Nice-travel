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
    private AbstractFindActivityService abstractFindActivityService;

    @Autowired
    private AbstractActivityService abstractActivityService;

    @GetMapping
    public List<ActivityDTO> getActivities(@RequestParam("scheduleDayId") Long scheduleDayId){
        return abstractFindActivityService.getActivities(scheduleDayId);
    }

    @PostMapping
    public ActivityEntity  addActivity(@Valid @RequestBody ActivityEntity activityEntity){
        /*FIXME Dessa forma você obriga que a aplicação tenha conhecimento da base de dados da API.
        * Modificar para enviar apenas as informações que são necessarias.*/
        return abstractActivityService.saveActivityOnDatabase(activityEntity);
    }

}
