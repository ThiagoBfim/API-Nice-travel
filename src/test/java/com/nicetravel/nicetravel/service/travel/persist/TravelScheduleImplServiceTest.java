package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.MockNicetravelApplicationTest;
import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.model.UserEntity;
import com.nicetravel.nicetravel.repository.CityRepository;
import com.nicetravel.nicetravel.repository.ScheduleTravelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TravelScheduleImplServiceTest extends MockNicetravelApplicationTest {

    @Autowired
    private AbstractTravelScheduleService travelScheduleService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ScheduleTravelRepository scheduleTravelRepository;

    @Test
    public void shouldSaveCity() {
        String placeId = "ChIJrTLr-GyuEmsRBfy61i59si0";
        travelScheduleService.saveCity(placeId);
        Assert.assertTrue(cityRepository.findByPlaceID(placeId).isPresent());
    }

    @Test
    public void shouldSaveScheduleTravel() {
        ScheduleTravelEntity scheduleTravelEntity = createScheduleTravel();
        Assert.assertNotNull(scheduleTravelEntity);
        Assert.assertEquals(5, scheduleTravelEntity.getScheduleDayEntities().size());
    }

    @Test
    public void shouldSaveScheduleTravelWithPrivateAccess() {
        ScheduleTravelEntity scheduleTravelEntity = createScheduleTravel();
        Assert.assertFalse(scheduleTravelEntity.getPublicAccess());
    }

    @Test
    public void shouldGivePublicAccesToSchedule() {
        ScheduleTravelEntity scheduleTravelEntity = createScheduleTravel();
        //Publish travel schedule
        boolean hasChangeAcessSchedule = travelScheduleService.publishTravelSchedule(scheduleTravelEntity.getCod());
        Assert.assertTrue(hasChangeAcessSchedule);
        Assert.assertTrue(scheduleTravelRepository.findById(scheduleTravelEntity.getCod()).get().getPublicAccess());
    }

    @Test
    public void shouldNotChangePublicAccesToSchedule() {
        boolean hasChangeAcessSchedule = travelScheduleService.publishTravelSchedule(9999L);
        Assert.assertFalse(hasChangeAcessSchedule);
    }

    @Test
    public void shouldCreateScheduleDTO() {
        ScheduleDTO scheduleDTO = travelScheduleService.generateTravelSchedule("ChIJrTLr-GyuEmsRBfy61i59si0", 2, "132", "emai@mail", "teste");
        Assert.assertNotNull(scheduleDTO);
    }

    @Test
    public void shouldIncrementVoteScheduleTravel() {
        ScheduleTravelEntity scheduleTravelEntity = createScheduleTravel();
        Assert.assertEquals(new Integer(0), scheduleTravelEntity.getNumberStar());
        boolean hasChangeAcessSchedule = travelScheduleService.voteTravelSchedule(scheduleTravelEntity.getCod(), "132", true);
        Assert.assertTrue(hasChangeAcessSchedule);
        Assert.assertEquals(new Integer(1), scheduleTravelRepository.findById(scheduleTravelEntity.getCod()).get().getNumberStar());
    }

    private ScheduleTravelEntity createScheduleTravel() {
        CityEntity cityEntity = travelScheduleService.saveCity("ChIJrTLr-GyuEmsRBfy61i59si0");
        UserEntity userOwner = travelScheduleService.saveOrUpdateUser("123", "teste@mail.com", "teste");
        return travelScheduleService.saveScheduleTravel(cityEntity, 5, userOwner);
    }

}
