package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;

public class MockTravelScheduleService extends AbstractTravelScheduleService {

    @Override
    public ScheduleDTO createSchedule(ScheduleTravelEntity scheduleTravelEntity) {
        return new ScheduleDTO()
                .setQtdDays(scheduleTravelEntity.getNumberDays())
                .setImageUrl(scheduleTravelEntity.getCityEntity().getPhotoLink())
                .setNameCity(scheduleTravelEntity.getCityEntity().getName())
                .setScheduleCod(scheduleTravelEntity.getCod())
                .setScheduleDay(null);
    }

    @Override
    protected ScheduleTravelEntity saveScheduleTravelOnDatabase(CityEntity cityEntity, int numberDays) {
        ScheduleTravelEntity travelEntity = new ScheduleTravelEntity();
        travelEntity.setCityEntity(cityEntity);
        travelEntity.setNumberDays(numberDays);
        return travelEntity;
    }

    @Override
    protected CityEntity saveCityOnDatabase(String cityName) {
        return createCityEntity(cityName);
    }

    private CityEntity createCityEntity(String cityName) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(cityName);
        cityEntity.setPhotoLink("https://s3.amazonaws.com/bk-static-prd-newctn/files/styles/discover_destaque/s3/2016-12/42%20-%20Salvador%20de%20Bahia_4.jpg?itok=2NW2cjVV");
        return cityEntity;
    }
}
