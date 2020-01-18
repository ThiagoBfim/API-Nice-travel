package com.nicetravel.nicetravel.service.scheduleday.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;
import com.nicetravel.nicetravel.repository.ScheduleDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class FindScheduleDayImplService extends AbstractFindScheduleDayService {

    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    @Override
    public List<ScheduleDayDTO> getScheduleDays(@NonNull Long scheduleId) {
        List<ScheduleDayEntity> scheduleDayEntities = scheduleDayRepository.findAllByScheduleTravelEntityCodOrderByDay(scheduleId);
        return scheduleDayEntities.stream().map(this::scheduleDayToDTO).collect(Collectors.toList());
    }

    private ScheduleDayDTO scheduleDayToDTO(ScheduleDayEntity scheduleDay) {
        return new ScheduleDayDTO(scheduleDay);
    }
}
