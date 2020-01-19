package com.nicetravel.nicetravel.mocks;

import com.nicetravel.nicetravel.service.external.PlaceDTO;

import java.util.Collections;

public class GoogleMapsAPIMock {

    public static PlaceDTO getMockPlaceDTO () {
        return new PlaceDTO().setName("teste")
                .setLat(123.45)
                .setLng(543.21)
                .setTypes("bar, bank")
                .setFormattedAddress("teste - DF")
                .setImageUrl(Collections.singletonList("http://"));
    }
}
