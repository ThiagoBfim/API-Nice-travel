package com.nicetravel.nicetravel.recource;

import com.nicetravel.nicetravel.MockNicetravelApplicationTest;
import com.nicetravel.nicetravel.dto.ActivityDTO;
import com.nicetravel.nicetravel.model.ActivityEntity;
import com.nicetravel.nicetravel.model.enuns.StyleActivity;
import com.nicetravel.nicetravel.repository.ActivityRepository;
import com.nicetravel.nicetravel.resource.ActivityResource;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityResourceTest extends MockNicetravelApplicationTest {

    @Autowired
    private ActivityResource activityResource;

    @MockBean
    private ActivityRepository activityRepository;

    @Test
    public void shouldRetriveActivities() {
        List<ActivityEntity> activities = new ArrayList<>();
        activities.add(mockActivity(1L));
        activities.add(mockActivity(2L));
        activities.add(mockActivity(3L));
        Mockito.when(activityRepository.findAllByScheduleDayEntityCod(Mockito.eq(11L))).thenReturn(activities);
        List<ActivityDTO> activitiesDTO = activityResource.getActivities(11L);
        Assertions.assertThat(activitiesDTO.stream().map(ActivityDTO::getId).collect(Collectors.toList()))
                .isEqualTo((activities.stream().map(ActivityEntity::getCod).collect(Collectors.toList())));
    }

    private ActivityEntity mockActivity(long cod) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setCod(cod);
        activityEntity.setDtStart(LocalTime.now());
        activityEntity.setStyleActivity(StyleActivity.OTHER);
        return activityEntity;
    }

    @Test
    public void shouldAddActivity() {
        ActivityDTO activityDTO = new ActivityDTO()
                .setIdScheduleDay(11L)
                .setStyleActivity(StyleActivity.OTHER.getDescription());
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setCod(9L);
        Mockito.when(activityRepository.save(Mockito.any())).thenReturn(activityEntity);
        ActivityDTO activityDTORetrieved = activityResource.addActivity(activityDTO);
        Assertions.assertThat(activityDTORetrieved.getId()).isEqualTo(activityEntity.getCod());
    }

    @Test
    public void shouldDeleteActivity() {
        activityResource.deleteActivity(99L);
        Mockito.verify(activityRepository).deleteById(Mockito.eq(99L));
    }

}
