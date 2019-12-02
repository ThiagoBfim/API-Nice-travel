package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.model.UserEntity;

public class MockTravelScheduleService extends AbstractTravelScheduleService {

    @Override
    public ScheduleDTO createScheduleDTO(ScheduleTravelEntity scheduleTravelEntity) {
        return new ScheduleDTO()
                .setQtdDays(scheduleTravelEntity.getScheduleDayEntities().size())
                .setImageUrl(scheduleTravelEntity.getCityEntity().getPhotoLink())
                .setCityAddress(scheduleTravelEntity.getCityEntity().getName())
                .setScheduleCod(scheduleTravelEntity.getCod());
    }

    @Override
    public boolean publishTravelSchedule(Long scheduleId) {
        return scheduleId == 1L;
    }

    @Override
    public boolean voteTravelSchedule(Long scheduleId, String userUID, Boolean positiveVote) {
        return false;
    }

    @Override
    public void delete(Long scheduleId) {
    }

    @Override
    protected UserEntity createOrGetUser(String userUID, String userEmail, String userName) {
        return new UserEntity();
    }

    @Override
    protected ScheduleTravelEntity saveScheduleTravel(CityEntity cityEntity, int numberDays, UserEntity userOwner) {
        ScheduleTravelEntity travelEntity = new ScheduleTravelEntity();
        travelEntity.setCod(1L);
        travelEntity.setCityEntity(cityEntity);
        travelEntity.setPublicAccess(Boolean.FALSE);
        travelEntity.setUserOwner(userOwner);
        return travelEntity;
    }

    @Override
    protected CityEntity saveCity(String cityName) {
        return createCityEntity(cityName);
    }

    private CityEntity createCityEntity(String placeID) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(placeID);
        cityEntity.setPhotoLink("https://s3.amazonaws.com/bk-static-prd-newctn/files/styles/discover_destaque/s3/2016-12/42%20-%20Salvador%20de%20Bahia_4.jpg?itok=2NW2cjVV");
        return cityEntity;
    }
}
