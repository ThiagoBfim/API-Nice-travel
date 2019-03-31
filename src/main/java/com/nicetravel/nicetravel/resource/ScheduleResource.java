package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.service.travel.persist.AbstractTravelScheduleService;
import com.nicetravel.nicetravel.service.travel.retrieve.AbstractFindTravelScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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

    @GetMapping("/days")
    public List<ScheduleDayDTO> getScheduleDaysByScheduleCod(@RequestParam("scheduleId") Long scheduleId) {
        return findTravelScheduleService.getScheduleDays(scheduleId);
    }

    @GetMapping("/ids")
    public ResponseEntity<?> publishTravelSchedule(@RequestParam("travelIds") List<Long> travelIds) {
        if (CollectionUtils.isEmpty(travelIds)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The parameter of 'travelIds' must have at least one element.");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(findTravelScheduleService.retrieveTravelSchedule(travelIds));
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
