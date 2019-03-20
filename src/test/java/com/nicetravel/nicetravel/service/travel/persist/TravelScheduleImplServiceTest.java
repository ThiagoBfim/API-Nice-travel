package com.nicetravel.nicetravel.service.travel.persist;

import com.nicetravel.nicetravel.exceptions.GooglePlaceNotFoundException;
import com.nicetravel.nicetravel.repository.CityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class TravelScheduleImplServiceTest {

    @Autowired
    private AbstractTravelScheduleService travelScheduleService;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void shouldSaveCity(){
        String placeId = "ChIJrTLr-GyuEmsRBfy61i59si0";
        travelScheduleService.saveCityOnDatabase(placeId);
        Assert.assertTrue(cityRepository.findByPlaceID(placeId).isPresent());
    }

    @Test(expected = GooglePlaceNotFoundException.class)
    public void shouldNotSaveCity(){
        String placeId = "FAKE_PLACE_ID";
        travelScheduleService.saveCityOnDatabase(placeId);
    }
}
