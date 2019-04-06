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
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleResourceTest extends MockNicetravelApplicationTest {

    @Autowired
    private ScheduleResource scheduleResource;

    @MockBean
    private ScheduleTravelRepository scheduleTravelRepository;

    @MockBean
    private ScheduleDayRepository scheduleDayRepository;

    @Test
    public void shouldReturnListScheduleDTO() {
        List<Long> ids = Arrays.asList(1L,2L);
        List<ScheduleTravelEntity> scheduleTravelEntityList = new ScheduleTravelListBuilder()
                .createTravelEntity()
                .createEntity(1)
                .createTravelEntity()
                .createEntity(2)
                .retrieveListTravel();
        Mockito.when(scheduleTravelRepository.findAllById(Mockito.eq(ids))).thenReturn(scheduleTravelEntityList);
        List<ScheduleDTO> schedulesByIds = scheduleResource.getSchedulesByIds(ids);
        Assert.assertEquals(2, schedulesByIds.size());
    }

    @Test
    public void shouldReturnTwoElementsWithSameCityName() {

        List<ScheduleTravelEntity> scheduleTravelEntityList = new ScheduleTravelListBuilder()
                .createTravelEntity()
                .createEntity(1)
                .createTravelEntity()
                .createEntity(2)
                .retrieveListTravel();
        Mockito.when(scheduleTravelRepository.findByCityEntityNameAndPublicAccess(Mockito.eq("CITY"), Mockito.eq(true), Mockito.any()))
                .thenReturn(scheduleTravelEntityList);

        List<ScheduleDTO> scheduleDTOS = scheduleResource.getSchedulesByCity("CITY", 2);
        Assert.assertEquals(2, scheduleDTOS.size());
        Assert.assertTrue(scheduleDTOS.stream().allMatch(t -> "CITY".equals(t.getNameCity())));
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

    /**
     * Esse builder utiliza interface fluente para tornar o código mais legivel
     */
    private static class ScheduleTravelListBuilder {
        List<ScheduleTravelEntity> scheduleTravel;

        ScheduleTravelListBuilder() {
            scheduleTravel = new ArrayList<>();
        }

        public ScheduleTravelBuilder createTravelEntity() {
            return new ScheduleTravelBuilder(this);
        }

        public List<ScheduleTravelEntity> retrieveListTravel() {
            return scheduleTravel;
        }

        public void addScheduleTravelEntity(ScheduleTravelEntity scheduleTravelEntity) {
            this.scheduleTravel.add(scheduleTravelEntity);
        }

        private static class ScheduleTravelBuilder {

            private ScheduleTravelEntity scheduleTravelEntity;
            private ScheduleTravelListBuilder scheduleTravelListBuilder;

            ScheduleTravelBuilder(ScheduleTravelListBuilder scheduleTravelListBuilder) {
                this.scheduleTravelListBuilder = scheduleTravelListBuilder;
                scheduleTravelEntity = new ScheduleTravelEntity();
            }

            private void includeCity() {
                CityEntity cityEntity = new CityEntity();
                cityEntity.setName("CITY");
                scheduleTravelEntity.setCityEntity(cityEntity);
            }

            ScheduleTravelListBuilder createEntity(long id) {
                scheduleTravelEntity.setCod(id);
                scheduleTravelEntity = new ScheduleTravelEntity();
                scheduleTravelEntity.setNumberDays(2);
                includeCity();
                scheduleTravelListBuilder.addScheduleTravelEntity(scheduleTravelEntity);
                return scheduleTravelListBuilder;
            }

        }
    }

}
