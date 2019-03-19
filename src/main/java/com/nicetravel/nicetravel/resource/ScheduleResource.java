package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.service.persist.AbstractTravelScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleResource {


    @Autowired
    private AbstractTravelScheduleService travelScheduleService;

//    @GetMapping("/city")
//    public ScheduleDTO getScheduleByCity(@RequestParam("cityName") String cityName) {
//        return travelScheduleService.getScheduleByCityName(cityName);
//    }

    @PostMapping
    public ScheduleDTO createTravelSchedule(@RequestParam("cityName") String cityName,
                                      @RequestParam("numberDays") int numberDays) {
        return travelScheduleService.generateTravelSchedule(cityName, numberDays);
    }

}
