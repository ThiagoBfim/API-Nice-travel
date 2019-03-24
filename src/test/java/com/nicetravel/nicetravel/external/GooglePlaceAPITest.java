package com.nicetravel.nicetravel.external;

import com.nicetravel.nicetravel.service.external.GoogleMapsAPI;
import com.nicetravel.nicetravel.service.external.PlaceDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GooglePlaceAPITest {


    @Autowired
    private GoogleMapsAPI googleMapsAPI;

    @Test
    public void testGetTypeLocation() {

        PlaceDTO placeDTO = googleMapsAPI.getPlaceDTO("ChIJIb5p4kuSqgcRUiZizPUS4TQ");

        Assert.assertEquals("natural_feature,establishment", placeDTO.getTypes());
        Assert.assertEquals(new Double(-8.4844765), placeDTO.getLat());
        Assert.assertEquals(new Double(-34.9996923), placeDTO.getLng());
        Assert.assertEquals("Porto de Galinhas", placeDTO.getName());
    }
}
