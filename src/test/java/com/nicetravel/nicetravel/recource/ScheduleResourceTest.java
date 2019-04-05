package com.nicetravel.nicetravel.recource;

import com.nicetravel.nicetravel.MockNicetravelApplicationTest;
import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.repository.ScheduleDayRepository;
import com.nicetravel.nicetravel.repository.ScheduleTravelRepository;
import com.nicetravel.nicetravel.resource.ScheduleResource;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleResourceTest extends MockNicetravelApplicationTest {

    @Autowired
    private ScheduleResource scheduleResource;

    @MockBean
    private ScheduleTravelRepository scheduleTravelRepository;

    @MockBean
    private ScheduleDayRepository scheduleDayRepository;


    @Test
    @Ignore
    public void shouldReturnListScheduleDTO() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        List<ScheduleDTO> schedulesByIds = scheduleResource.getSchedulesByIds(Collections.singletonList(1L));

    }

    @Test
    public void shouldReturnListScheduleDaysDTO() {
        List<ScheduleDayEntity> scheduleDayEntities = new ArrayList<>();
        ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
        scheduleDayEntity.setDay(1);
        scheduleDayEntities.add(scheduleDayEntity);
        scheduleDayEntities.add(scheduleDayEntity);

        Mockito.when(scheduleDayRepository.findAllByScheduleTravelEntityCod(Mockito.eq(1L))).thenReturn(scheduleDayEntities);
        List<ScheduleDayDTO> scheduleDaysByScheduleCod = scheduleResource.getScheduleDaysByScheduleCod(1L);
        Assert.assertEquals(2, scheduleDaysByScheduleCod.size());
    }

    @Test
    public void shouldReturnTwoElementsWithSameCityName() {

        List<ScheduleTravelEntity> scheduleTravel = new ArrayList<>();
        ScheduleTravelEntity scheduleTravelEntity = createScheduleTravel();
        scheduleTravel.add(scheduleTravelEntity);
        scheduleTravel.add(scheduleTravelEntity);
        Mockito.when(scheduleTravelRepository.findByCityEntityNameAndPublicAccess(Mockito.eq("CITY"), Mockito.eq(true), Mockito.any()))
                .thenReturn(scheduleTravel);

        List<ScheduleDTO> scheduleDTOS = scheduleResource.getSchedulesByCity("CITY", 2);
        Assert.assertEquals(2, scheduleDTOS.size());
        Assert.assertTrue(scheduleDTOS.stream().allMatch(t -> "CITY".equals(t.getNameCity())));
    }

    private ScheduleTravelEntity createScheduleTravel() {
        ScheduleTravelEntity scheduleTravelEntity = new ScheduleTravelEntity();
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("CITY");
        scheduleTravelEntity.setNumberDays(2);
        scheduleTravelEntity.setCityEntity(cityEntity);
        return scheduleTravelEntity;
    }
}
