package com.nicetravel.nicetravel;

import com.nicetravel.nicetravel.mocks.GoogleMapsAPIMock;
import com.nicetravel.nicetravel.service.external.GoogleMapsAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class NicetravelApplicationMockApisTests {

    @MockBean
    private GoogleMapsAPI googleMapsAPI;

    @Before
    public void setup() {
        Mockito.when(googleMapsAPI.getPlaceDTO(Mockito.any())).thenReturn(GoogleMapsAPIMock.getMockPlaceDTO());
    }

    @Test
    public void contextLoads() {
    }

}
