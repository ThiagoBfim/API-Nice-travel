package com.nicetravel.nicetravel.service.activity.retrieve;

import com.nicetravel.nicetravel.dto.ActivityDTO;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MockFindActivityService extends AbstractFindActivityService{
    @Override
    public List<ActivityDTO> getActivities(Long scheduleDayId) {
        List<ActivityDTO> lista = new ArrayList<>();
        for (int i =0;i<9;i++){
            lista.add(
                    new ActivityDTO()
                        .setNameOfPlace("Torre Eifel")
                    .setDescription("Torre em paris")
                    .setStartActivity(LocalTime.now())
                    .setFinishActivity(LocalTime.now())
                    .setPrice(new BigDecimal(10))
                    .setStyleActivity("BAR")
            );
        }
        return lista;
    }
}
