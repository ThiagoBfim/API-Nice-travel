package com.nicetravel.nicetravel.service.activity.retrieve;

import com.nicetravel.nicetravel.dto.ActivityDTO;
import com.nicetravel.nicetravel.model.ActivityEntity;
import com.nicetravel.nicetravel.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FindActivityImplService extends AbstractFindActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<ActivityDTO> getActivities(Long scheduleDayId) {
        List<ActivityEntity> activities = activityRepository.findAllByScheduleDayEntityCod(scheduleDayId);
        return activities
                .stream()
                .map(this::activityEntityToDTO)
                .sorted(Comparator.comparing(ActivityDTO::getStartActivity))
                .collect(Collectors.toList());
    }


    private ActivityDTO activityEntityToDTO(ActivityEntity activityEntity) {
        return new ActivityDTO(activityEntity);
    }
}
