package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.exceptions.GooglePlaceNotFoundException;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.repository.CityRepository;
import com.nicetravel.nicetravel.repository.ScheduleTravelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class TravelScheduleImplServiceTest {

    @Autowired
    private AbstractTravelScheduleService travelScheduleService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ScheduleTravelRepository scheduleTravelRepository;

    @Test
    public void shouldSaveCity() {
        String placeId = "ChIJrTLr-GyuEmsRBfy61i59si0";
        travelScheduleService.saveCityOnDatabase(placeId);
        Assert.assertTrue(cityRepository.findByPlaceID(placeId).isPresent());
    }

    @Test(expected = GooglePlaceNotFoundException.class)
    public void shouldNotSaveCity() {
        String placeId = "FAKE_PLACE_ID";
        travelScheduleService.saveCityOnDatabase(placeId);
    }

    @Test
    public void shouldSaveScheduleTravel() {
        CityEntity cityEntity = travelScheduleService.saveCityOnDatabase("ChIJrTLr-GyuEmsRBfy61i59si0");
        ScheduleTravelEntity scheduleTravelEntity = travelScheduleService.saveScheduleTravelOnDatabase(cityEntity, 5);
        Assert.assertNotNull(scheduleTravelEntity);
    }

    @Test
    public void shouldSaveScheduleTravelWithPrivateAccess() {
        CityEntity cityEntity = travelScheduleService.saveCityOnDatabase("ChIJrTLr-GyuEmsRBfy61i59si0");
        ScheduleTravelEntity scheduleTravelEntity = travelScheduleService.saveScheduleTravelOnDatabase(cityEntity, 5);
        Assert.assertNotNull(scheduleTravelEntity);
        Assert.assertFalse(scheduleTravelEntity.getPublicAccess());
    }

    @Test
    public void shouldGivePublicAccesToSchedule() {
        CityEntity cityEntity = travelScheduleService.saveCityOnDatabase("ChIJrTLr-GyuEmsRBfy61i59si0");
        ScheduleTravelEntity scheduleTravelEntity = travelScheduleService.saveScheduleTravelOnDatabase(cityEntity, 5);
        Assert.assertNotNull(scheduleTravelEntity);
        Assert.assertFalse(scheduleTravelEntity.getPublicAccess());

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
        ScheduleDTO scheduleDTO = travelScheduleService.generateTravelSchedule("ChIJrTLr-GyuEmsRBfy61i59si0", 2);
        Assert.assertNotNull(scheduleDTO);
        Assert.assertNull(scheduleDTO.getScheduleDay());
    }

}
