package com.nicetravel.nicetravel.service.travel.retrieve;

import com.github.javafaker.Faker;
import com.nicetravel.nicetravel.dto.ScheduleDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockFindTravelScheduleService extends AbstractFindTravelScheduleService {

    @Override
    public List<ScheduleDTO> getScheduleByPlaceID(String placeID, Integer sizeElements) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (int i = 1; i <= sizeElements; i++) {
            ScheduleDTO scheduleTravel = createScheduleTravel(placeID, new Random().nextInt(10) + 1);
            scheduleDTOS.add(scheduleTravel);
        }
        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> retrieveTravelSchedule(List<Long> scheduleIds) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (int i = 1; i <= scheduleIds.size(); i++) {
            ScheduleDTO scheduleTravel = createScheduleTravel(new Faker().address().cityName(), new Random().nextInt(10) + 1);
            scheduleDTOS.add(scheduleTravel);
        }
        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> retrieveTravelScheduleByUserUID(String userUID) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            ScheduleDTO scheduleTravel = createScheduleTravel(new Faker().address().cityName(), new Random().nextInt(10) + 1);
            scheduleDTOS.add(scheduleTravel);
        }
        return scheduleDTOS;
    }

    private ScheduleDTO createScheduleTravel(String cityName, int qtdDias) {
        return new ScheduleDTO()
                .setUserName("Joaquim")
                .setQtdDays(qtdDias)
                .setCityAddress(cityName)
                .setScheduleCod(qtdDias + 1L)
                .setPriceFinal(BigDecimal.TEN)
                .setImageUrl("https://s3.amazonaws.com/bk-static-prd-newctn/files/styles/discover_destaque/s3/2016-12/42%20-%20Salvador%20de%20Bahia_4.jpg?itok=2NW2cjVV");
    }

}
