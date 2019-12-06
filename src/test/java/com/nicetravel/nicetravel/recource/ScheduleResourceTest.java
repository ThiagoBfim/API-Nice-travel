package com.nicetravel.nicetravel.recource;

import com.nicetravel.nicetravel.MockNicetravelApplicationTest;
import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.CityEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.model.UserEntity;
import com.nicetravel.nicetravel.model.VoteScheduleEntity;
import com.nicetravel.nicetravel.repository.ScheduleTravelRepository;
import com.nicetravel.nicetravel.repository.VoteScheduleRepository;
import com.nicetravel.nicetravel.resource.ScheduleResource;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ScheduleResourceTest extends MockNicetravelApplicationTest {

    @Autowired
    private ScheduleResource scheduleResource;

    @MockBean
    private ScheduleTravelRepository scheduleTravelRepository;

    @MockBean
    private VoteScheduleRepository voteScheduleRepository;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ScheduleTravelEntity scheduleTravelEntity;

    @Test
    public void shouldReturnListScheduleDTO() {
        List<Long> ids = Arrays.asList(1L, 2L);
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
    public void shouldIncreaseVoteAndReturnTrue() {
        ScheduleTravelEntity scheduleTravelEntity = new ScheduleTravelEntity();
        scheduleTravelEntity.setCod(1L);
        scheduleTravelEntity.setNumberStar(0);

        Mockito.when(scheduleTravelRepository.findById(Mockito.eq(1L)))
                .thenReturn(Optional.of(scheduleTravelEntity));

        Mockito.when(voteScheduleRepository.save(Mockito.any()))
                .thenReturn(new VoteScheduleEntity());
        boolean increaseVote = scheduleResource.voteTravelSchedule(1L,"123", Boolean.TRUE);
        Assert.assertTrue(increaseVote);
    }

    @Test
    public void shouldReturnTwoElementsWithSameCityName() {
        List<ScheduleTravelEntity> scheduleTravelEntityList = new ScheduleTravelListBuilder()
                .createTravelEntity()
                .createEntity(1)
                .createTravelEntity()
                .createEntity(2)
                .retrieveListTravel();
        Mockito.when(scheduleTravelRepository.findByCityEntityPlaceIDAndPublicAccess(Mockito.eq("12345"), Mockito.eq(true), Mockito.any()))
                .thenReturn(scheduleTravelEntityList);

        List<ScheduleDTO> scheduleDTOS = scheduleResource.getSchedulesByCity("12345", 2);
        Assert.assertEquals(2, scheduleDTOS.size());
        Assert.assertTrue(scheduleDTOS.stream().allMatch(t -> "CITY - DF".equals(t.getCityAddress())));
    }

    @Test
    public void shouldCreateTravel() {
        Mockito.when(scheduleTravelRepository.save(Mockito.any())).thenReturn(scheduleTravelEntity);
        ScheduleDTO travelSchedule = scheduleResource.createTravelSchedule("123", 5, "132", "emai@mail", "teste");
        Assert.assertNotNull(travelSchedule);
    }

    @Test
    public void shouldPublishTravelSchedule() {
        ScheduleTravelEntity spy = Mockito.spy(ScheduleTravelEntity.class);
        Mockito.when(scheduleTravelRepository.findById(Mockito.eq(1L))).thenReturn(Optional.of(spy));
        scheduleResource.publishTravelSchedule(1L);
        Assertions.assertThat(spy.getPublicAccess()).isEqualTo(Boolean.TRUE);
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
                scheduleTravelEntity = new ScheduleTravelEntity();
                this.scheduleTravelListBuilder = scheduleTravelListBuilder;
            }

            private void includeCity() {
                CityEntity cityEntity = new CityEntity();
                cityEntity.setFormattedAddress("CITY - DF");
                scheduleTravelEntity.setCityEntity(cityEntity);
            }

            ScheduleTravelListBuilder createEntity(long id) {
                scheduleTravelEntity.setCod(id);
                scheduleTravelEntity.setUserOwner(new UserEntity());
                includeCity();
                scheduleTravelListBuilder.addScheduleTravelEntity(scheduleTravelEntity);
                return scheduleTravelListBuilder;
            }


        }
    }

}
