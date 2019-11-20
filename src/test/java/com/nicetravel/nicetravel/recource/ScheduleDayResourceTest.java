package com.nicetravel.nicetravel.recource;

import com.nicetravel.nicetravel.MockNicetravelApplicationTest;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.repository.ScheduleDayRepository;
import com.nicetravel.nicetravel.repository.ScheduleTravelRepository;
import com.nicetravel.nicetravel.resource.ScheduleDayResource;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDayResourceTest extends MockNicetravelApplicationTest {

    @Autowired
    private ScheduleDayResource scheduleDayResource;

    @MockBean
    private ScheduleDayRepository scheduleDayRepository;

    @MockBean
    private ScheduleTravelRepository scheduleTravelRepository;

    @Test
    public void shouldReturnListScheduleDaysDTO() {
        List<ScheduleDayEntity> scheduleDayEntities = new ArrayList<>();
        ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
        scheduleDayEntity.setDay(1);
        scheduleDayEntity.setCod(1L);
        scheduleDayEntities.add(scheduleDayEntity);
        scheduleDayEntities.add(scheduleDayEntity);

        Mockito.when(scheduleDayRepository.findAllByScheduleTravelEntityCod(Mockito.eq(1L))).thenReturn(scheduleDayEntities);
        List<ScheduleDayDTO> scheduleDaysByScheduleCod = scheduleDayResource.getScheduleDaysByScheduleCod(1L);
        Assert.assertEquals(2, scheduleDaysByScheduleCod.size());
    }

    @Test
    public void shouldDeleteScheduleDays() {
        scheduleDayResource.removeByScheduleDayId(1L);
        Mockito.verify(scheduleDayRepository).deleteById(Mockito.eq(1L));
    }

    @Test
    public void shouldAddScheduleDays() {
        ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
        scheduleDayEntity.setDay(1);
        scheduleDayEntity.setCod(1L);
        Mockito.when(scheduleTravelRepository.getOne(Mockito.eq(1L))).thenReturn(Mockito.mock(ScheduleTravelEntity.class));
        Mockito.when(scheduleDayRepository.save(Mockito.any())).thenReturn(scheduleDayEntity);
        ScheduleDayDTO scheduleDayDTO = scheduleDayResource.addScheduleDay(1L);
        Assertions.assertThat(scheduleDayDTO.getId()).isEqualTo(scheduleDayEntity.getCod());
        Assertions.assertThat(scheduleDayDTO.getDay()).isEqualTo(scheduleDayEntity.getDay());
    }

}
