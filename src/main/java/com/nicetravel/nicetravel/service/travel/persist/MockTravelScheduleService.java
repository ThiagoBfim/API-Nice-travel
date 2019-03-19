package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ActivityDTO;
import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ActivityEntity;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.model.enuns.StyleActivity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MockTravelScheduleService extends AbstractTravelScheduleService {

    @Override
    public ScheduleDTO createSchedule(ScheduleTravelEntity scheduleTravelEntity) {
        List<ScheduleDayDTO> scheduleDayDTOS = new ArrayList<>();
        scheduleTravelEntity.getScheduleDayEntities()
                .forEach(scheduleDayEntity -> {
                    List<ActivityDTO> activityDTOList = new ArrayList<>();
                    scheduleDayEntity.getActivities().forEach(activityEntity -> activityDTOList.add(new ActivityDTO()
                            .setPrice(activityEntity.getPrice())
                            .setFinishActivity(activityEntity.getDtEnd())
                            .setStartActivity(activityEntity.getDtStart())
                            .setDescription(activityEntity.getDescription())
                            .setNameOfPlace(activityEntity.getName())));
                    scheduleDayDTOS.add(new ScheduleDayDTO()
                            .setDay(scheduleDayEntity.getDay())
                            .setActivities(activityDTOList));
                });
        return new ScheduleDTO()
                .setQtdDays(scheduleTravelEntity.getNumberDays())
                .setImageUrl(scheduleTravelEntity.getCityEntity().getPhotoLink())
                .setNameCity(scheduleTravelEntity.getCityEntity().getName())
                .setScheduleDay(scheduleDayDTOS);
    }

    @Override
    protected ScheduleTravelEntity saveScheduleTravelOnDatabase(CityEntity cityEntity, int numberDays) {
        ScheduleTravelEntity travelEntity = new ScheduleTravelEntity();
        travelEntity.setCityEntity(cityEntity);
        travelEntity.setNumberDays(numberDays);

        List<ScheduleDayEntity> scheduleDayEntities = new ArrayList<>();
        for (int i = 1; i <= numberDays; i++) {
            ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
            scheduleDayEntity.setDay(i);
            scheduleDayEntity.setActivities(createActivities());
            scheduleDayEntities.add(scheduleDayEntity);

        }
        travelEntity.setScheduleDayEntities(scheduleDayEntities);
        return travelEntity;
    }

    @Override
    protected CityEntity saveCityOnDatabase(String cityName) {
        return createCityEntity(cityName);
    }

    private List<ActivityEntity> createActivities() {
        List<ActivityEntity> activityEntities = new ArrayList<>();

        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setDescription("Faby Buggy apresenta o que tem de melhor no nosso lindo litoral Sul ,PORTO DE GALINHAS/PE e Regiões , " +
                "aqui vocês vão encontrar as opções de Lazer,cultura e Diversões .. Somos uma equipe que trabalhamos para seu " +
                "lazer e sossego com total segurança Responsabilidade e pontualidade");
        activityEntity.setDtEnd(LocalTime.of(18, 00));
        activityEntity.setDtStart(LocalTime.of(7, 30));
        activityEntity.setPrice(new BigDecimal(250));
        activityEntity.setStyleActivity(StyleActivity.SPORT);
        activityEntity.setName("Faby Buggy Turismo");

        activityEntities.add(activityEntity);
        ActivityEntity activityEntity2 = new ActivityEntity();
        activityEntity2.setDescription("Tomar um banho e se prepara para noite.");
        activityEntity2.setDtEnd(LocalTime.of(19, 30));
        activityEntity2.setDtStart(LocalTime.of(19, 00));
        activityEntity2.setPrice(new BigDecimal(0));
        activityEntity2.setStyleActivity(StyleActivity.OTHER);
        activityEntity2.setName("Tomar Banho e se arrumar");
        activityEntities.add(activityEntity2);


        activityEntities.add(activityEntity);
        ActivityEntity activityEntity3 = new ActivityEntity();
        activityEntity3.setName("Cabidela da Natália");
        activityEntity3.setDescription("Pratos típicos muito bons. O carro chefe da casa, a galinha à cabidela, " +
                "é imperdível. Cardápio variado: carnes, aves, peixes e pizzas.");
        activityEntity3.setDtEnd(LocalTime.of(21, 00));
        activityEntity3.setDtStart(LocalTime.of(19, 30));
        activityEntity3.setPrice(new BigDecimal(0));
        activityEntity3.setStyleActivity(StyleActivity.RESTAURANT);
        activityEntities.add(activityEntity3);

        return activityEntities;

    }

    private CityEntity createCityEntity(String cityName) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(cityName);
        cityEntity.setPhotoLink("https://s3.amazonaws.com/bk-static-prd-newctn/files/styles/discover_destaque/s3/2016-12/42%20-%20Salvador%20de%20Bahia_4.jpg?itok=2NW2cjVV");
        return cityEntity;
    }
}
