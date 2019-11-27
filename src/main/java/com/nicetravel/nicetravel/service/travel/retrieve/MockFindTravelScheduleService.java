package com.nicetravel.nicetravel.service.travel.retrieve;

import com.github.javafaker.Faker;
import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockFindTravelScheduleService extends AbstractFindTravelScheduleService {

    @Override
    public List<ScheduleDTO> getScheduleByCityName(String cityName, Integer sizeElements) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (int i = 1; i <= sizeElements; i++) {
            ScheduleDTO scheduleTravel = createScheduleTravel(cityName, new Random().nextInt(10) + 1);
            scheduleDTOS.add(scheduleTravel);
        }
        return scheduleDTOS;
    }

    private List<ScheduleDayDTO> getScheduleDays(Long scheduleId) {
        List<ScheduleDayDTO> scheduleDayDTOS = new ArrayList<>();
        for (int i = 1; i <= scheduleId; i++) {
            scheduleDayDTOS.add(new ScheduleDayDTO()
                    .setDay(i)
                    .setPriceDay(new BigDecimal(new Random().nextInt(250) + 100.21)));
        }
        return scheduleDayDTOS;
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

    private ScheduleDTO createScheduleTravel(String cityName, int qtdDias) {
        return new ScheduleDTO()
                .setUserName("Joaquim")
                .setQtdDays(qtdDias)
                .setNameCity(cityName)
                .setScheduleCod(qtdDias + 1L)
                .setPriceFinal(calculatePriceTravel(getScheduleDays(1L)))
                .setImageUrl("https://s3.amazonaws.com/bk-static-prd-newctn/files/styles/discover_destaque/s3/2016-12/42%20-%20Salvador%20de%20Bahia_4.jpg?itok=2NW2cjVV");
    }


    private BigDecimal calculatePriceTravel(List<ScheduleDayDTO> scheduleDay){
        if (CollectionUtils.isEmpty(scheduleDay)) {
            return  BigDecimal.ZERO;
        }
        return scheduleDay
                .stream()
                .filter(s -> s.getPriceDay() != null)
                .map(ScheduleDayDTO::getPriceDay)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
