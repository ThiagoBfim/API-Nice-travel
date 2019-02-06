package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.enuns.StyleTravel;
import com.nicetravel.nicetravel.service.ITravelScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleResource {


    @Autowired
    private ITravelScheduleService travelScheduleService;

    @GetMapping
    public ScheduleDTO criarCategoria(@RequestParam("cityName") String cityName,
                                      @RequestParam("numberDays") int numberDays,
                                      @RequestParam("styleTravel") StyleTravel styleTravel) {
        return travelScheduleService.generateTravelSchedule(cityName, numberDays, styleTravel);
    }

}
