package com.nicetravel.nicetravel.external;

import com.nicetravel.nicetravel.NicetravelApplicationTests;
import com.nicetravel.nicetravel.exceptions.GooglePlaceNotFoundException;
import com.nicetravel.nicetravel.service.external.GoogleMapsAPI;
import com.nicetravel.nicetravel.service.external.PlaceDTO;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore("This test will consult Google API. " +
        "To enable this test have to be used with propertie: nice-travel.google.enabled=true.")
public class GooglePlaceAPITest extends NicetravelApplicationTests {

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

    @Test(expected = GooglePlaceNotFoundException.class)
    public void shouldNotSaveCity() {
        googleMapsAPI.getPlaceDTO("FAKE_PLACE_ID");
    }

}
