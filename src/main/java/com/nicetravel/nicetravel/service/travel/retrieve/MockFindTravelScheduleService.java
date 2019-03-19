package com.nicetravel.nicetravel.service.travel.retrieve;

import com.nicetravel.nicetravel.dto.ActivityDTO;
import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;

import java.math.BigDecimal;
import java.time.LocalTime;
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

    private ScheduleDTO createScheduleTravel(String cityName, int qtdDias) {
        List<ScheduleDayDTO> scheduleDayDTOS = new ArrayList<>();
        for (int i = 1; i <= qtdDias; i++) {
            scheduleDayDTOS.add(new ScheduleDayDTO()
                    .setActivities(createActivities())
                    .setDay(i));
        }
        return new ScheduleDTO()
                .setQtdDays(qtdDias)
                .setNameCity(cityName)
                .setScheduleCod(qtdDias + 1L)
                .setImageUrl("https://s3.amazonaws.com/bk-static-prd-newctn/files/styles/discover_destaque/s3/2016-12/42%20-%20Salvador%20de%20Bahia_4.jpg?itok=2NW2cjVV")
                .setScheduleDay(scheduleDayDTOS);
    }

    private List<ActivityDTO> createActivities() {
        List<ActivityDTO> activityDTOList = new ArrayList<>();
        activityDTOList.add(new ActivityDTO()
                .setNameOfPlace("Faby Buggy Turismo")
                .setDescription("Faby Buggy apresenta o que tem de melhor no nosso lindo litoral Sul ,PORTO DE GALINHAS/PE e Regiões , " +
                        "aqui vocês vão encontrar as opções de Lazer,cultura e Diversões .. Somos uma equipe que trabalhamos para seu " +
                        "lazer e sossego com total segurança Responsabilidade e pontualidade")
                .setStartActivity(LocalTime.of(7, 30))
                .setFinishActivity(LocalTime.of(18, 00))
                .setPrice(new BigDecimal(new Random().nextInt(250) + 100)));

        activityDTOList.add(new ActivityDTO()
                .setNameOfPlace("Tomar Banho e se arrumar")
                .setDescription("Tomar um banho e se prepara para noite.")
                .setStartActivity(LocalTime.of(18, 00))
                .setFinishActivity(LocalTime.of(19, 30)));

        activityDTOList.add(new ActivityDTO()
                .setNameOfPlace("Cabidela da Natália")
                .setDescription("Pratos típicos muito bons. O carro chefe da casa, a galinha à cabidela, " +
                        "é imperdível. Cardápio variado: carnes, aves, peixes e pizzas.")
                .setStartActivity(LocalTime.of(19, 40))
                .setFinishActivity(LocalTime.of(21, 20))
                .setPrice(new BigDecimal(new Random().nextInt(60) + 15)));


        activityDTOList.add(new ActivityDTO()
                .setNameOfPlace("Birosca da Cachaça")
                .setDescription("Cachaças de vários sabores, com diversidade de gosto, preços de alcance para todos, muita variedade, com frutas, madeiras")
                .setStartActivity(LocalTime.of(21, 40))
                .setFinishActivity(LocalTime.of(23, 59))
                .setPrice(new BigDecimal(new Random().nextInt(120))));
        return activityDTOList;
    }
}
