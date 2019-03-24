package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.service.travel.persist.AbstractTravelScheduleService;
import com.nicetravel.nicetravel.service.travel.retrieve.AbstractFindTravelScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleResource {

    @Autowired
    private AbstractTravelScheduleService travelScheduleService;

    @Autowired
    private AbstractFindTravelScheduleService findTravelScheduleService;

    @GetMapping("/city")
    public List<ScheduleDTO> getScheduleByCity(@RequestParam("cityName") String cityName,
                                               @RequestParam(value = "sizeElements", required = false, defaultValue = "1") Integer sizeElements) {
        return findTravelScheduleService.getScheduleByCityName(cityName, sizeElements);
    }

    @PostMapping
    public ScheduleDTO createTravelSchedule(@RequestParam("placeID") String placeID,
                                            @RequestParam("numberDays") int numberDays) {
        return travelScheduleService.generateTravelSchedule(placeID, numberDays);
    }

    @PostMapping("/publish")
    public boolean publishTravelSchedule(@RequestParam("travelId") Long travelId) {
        return travelScheduleService.publishTravelSchedule(travelId);
    }

}
