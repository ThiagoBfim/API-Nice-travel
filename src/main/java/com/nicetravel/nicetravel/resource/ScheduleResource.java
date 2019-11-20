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
    public List<ScheduleDTO> getSchedulesByCity(@RequestParam("cityName") String cityName,
                                                @RequestParam(value = "sizeElements", required = false, defaultValue = "1") Integer sizeElements) {
        ResourceUtil.validateValue(cityName, "cityName");
        return findTravelScheduleService.getScheduleByCityName(cityName, sizeElements);
    }

    @GetMapping("/ids")
    public List<ScheduleDTO> getSchedulesByIds(@RequestParam("scheduleIds") List<Long> scheduleIds) {
        ResourceUtil.validateValue(scheduleIds, "scheduleIds");
        return findTravelScheduleService.retrieveTravelSchedule(scheduleIds);
    }

    @PostMapping
    public ScheduleDTO createTravelSchedule(@RequestParam("placeID") String placeID,
                                            @RequestParam("numberDays") Integer numberDays) {
        ResourceUtil.validateValue(numberDays, "numberDays");
        ResourceUtil.validateValue(placeID, "placeID");
        return travelScheduleService.generateTravelSchedule(placeID, numberDays);
    }

    @PostMapping("/publish")
    public boolean publishTravelSchedule(@RequestParam("scheduleId") Long scheduleId) {
        ResourceUtil.validateValue(scheduleId, "scheduleId");
        return travelScheduleService.publishTravelSchedule(scheduleId);
    }

    @PostMapping("/vote")
    public boolean voteTravelSchedule(@RequestParam("scheduleId") Long scheduleId,
                                      @RequestParam(value = "positiveVote", required = false, defaultValue = "true") Boolean positiveVote) {
        return travelScheduleService.voteTravelSchedule(scheduleId, positiveVote);
    }

}
