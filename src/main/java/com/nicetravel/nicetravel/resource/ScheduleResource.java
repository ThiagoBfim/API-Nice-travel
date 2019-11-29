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
    public List<ScheduleDTO> getSchedulesByCity(@RequestParam(value = "placeID", required = false) String placeID,
                                                @RequestParam(value = "sizeElements", required = false, defaultValue = "1") Integer sizeElements) {
        return findTravelScheduleService.getScheduleByPlaceID(placeID, sizeElements);
    }

    @GetMapping("/ids")
    public List<ScheduleDTO> getSchedulesByIds(@RequestParam("scheduleIds") List<Long> scheduleIds) {
        ResourceUtil.validateValue(scheduleIds, "scheduleIds");
        return findTravelScheduleService.retrieveTravelSchedule(scheduleIds);
    }

    @GetMapping("/userUID")
    public List<ScheduleDTO> getSchedulesByUserUID(@RequestParam("userUID") String userUID) {
        ResourceUtil.validateValue(userUID, "userUID");
        return findTravelScheduleService.retrieveTravelScheduleByUserUID(userUID);
    }

    @PostMapping
    public ScheduleDTO createTravelSchedule(@RequestParam("placeID") String placeID,
                                            @RequestParam("numberDays") Integer numberDays,
                                            @RequestParam("userUID") String userUID,
                                            @RequestParam("userEmail") String userEmail,
                                            @RequestParam("userName") String userName) {
        ResourceUtil.validateValue(numberDays, "numberDays");
        ResourceUtil.validateValue(placeID, "placeID");
        ResourceUtil.validateValue(userUID, "userUID");
        ResourceUtil.validateValue(userEmail, "userEmail");
        ResourceUtil.validateValue(userName, "userName");
        return travelScheduleService.generateTravelSchedule(placeID, numberDays, userUID, userEmail, userName);
    }

    @DeleteMapping("/delete")
    public void deleteTravelSchedule(@RequestParam("scheduleId") Long scheduleId) {
        ResourceUtil.validateValue(scheduleId, "scheduleId");
        travelScheduleService.delete(scheduleId);
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
