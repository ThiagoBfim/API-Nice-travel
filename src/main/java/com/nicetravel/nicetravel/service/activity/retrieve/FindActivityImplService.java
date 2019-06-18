package com.nicetravel.nicetravel.service.activity.retrieve;

import com.nicetravel.nicetravel.dto.ActivityDTO;
import com.nicetravel.nicetravel.model.ActivityEntity;
import com.nicetravel.nicetravel.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public class FindActivityImplService extends AbstractFindActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<ActivityDTO> getActivities() {
        List<ActivityEntity> lista =  activityRepository.findAll();
        return lista.stream().map(this::activityEntityToDTO).collect(Collectors.toList());
    }


    private ActivityDTO activityEntityToDTO(ActivityEntity activityEntity) {
        return new ActivityDTO()
                .setStyleActivity(activityEntity.getStyleActivity().getDescription())
                .setPrice(activityEntity.getPrice())
                .setStartActivity(activityEntity.getDtStart())
                .setFinishActivity(activityEntity.getDtEnd())
                .setNameOfPlace(activityEntity.getName())
                .setDescription(activityEntity.getDescription());
    }
}
