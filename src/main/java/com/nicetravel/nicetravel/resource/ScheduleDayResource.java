package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.service.scheduleday.persist.AbstractScheduleDayService;
import com.nicetravel.nicetravel.service.scheduleday.retrieve.AbstractFindScheduleDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduleDay")
public class ScheduleDayResource {

    @Autowired
    private AbstractScheduleDayService scheduleDayService;

    @Autowired
    private AbstractFindScheduleDayService findScheduleDayService;


    @GetMapping("/days")
    public List<ScheduleDayDTO> getScheduleDaysByScheduleCod(@RequestParam("scheduleId") Long scheduleId) {
        ResourceUtil.validateValue(scheduleId, "scheduleId");
        return findScheduleDayService.getScheduleDays(scheduleId);
    }

    @PostMapping("/add")
    public ScheduleDayDTO addScheduleDay(@RequestParam("scheduleId") Long scheduleId) {
        ResourceUtil.validateValue(scheduleId, "scheduleId");
        return scheduleDayService.addScheduleDay(scheduleId);
    }

    @DeleteMapping("/delete")
    public void deleteByScheduleDayId(@RequestParam("scheduleDayId") Long scheduleDayId) {
        ResourceUtil.validateValue(scheduleDayId, "scheduleDayId");
        scheduleDayService.deleteById(scheduleDayId);
    }

    @PostMapping("/reorder")
    public void reorderScheduleDays(@RequestParam("scheduleDayIdFrom") Long scheduleDayIdFrom,
                                    @RequestParam("scheduleDayIdTo") Long scheduleDayIdTo) {
        ResourceUtil.validateValue(scheduleDayIdFrom, "scheduleDayIdFrom");
        ResourceUtil.validateValue(scheduleDayIdTo, "scheduleDayIdTo");
        scheduleDayService.reorder(scheduleDayIdFrom, scheduleDayIdTo);
    }

}
