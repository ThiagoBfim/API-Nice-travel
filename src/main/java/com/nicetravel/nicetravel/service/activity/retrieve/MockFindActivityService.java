package com.nicetravel.nicetravel.service.activity.retrieve;

import com.nicetravel.nicetravel.dto.ActivityDTO;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockFindActivityService extends AbstractFindActivityService {

    @Override
    public List<ActivityDTO> getActivities(Long scheduleDayId) {
        return createActivities();
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
                .setPrice(new BigDecimal(new Random().nextInt(250) + 100.21)));

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
