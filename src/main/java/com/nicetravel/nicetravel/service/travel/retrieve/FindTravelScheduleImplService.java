package com.nicetravel.nicetravel.service.travel.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.repository.ScheduleTravelRepository;
import com.nicetravel.nicetravel.repository.util.PagableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class FindTravelScheduleImplService extends AbstractFindTravelScheduleService {

    @Autowired
    private ScheduleTravelRepository scheduleTravelRepository;

    @Override
    public List<ScheduleDTO> getScheduleByCityName(String cityName, @NonNull Integer sizeElements) {
        List<ScheduleTravelEntity> scheduleTravelEntityList;
        if (cityName == null) {
            scheduleTravelEntityList = scheduleTravelRepository
                    .findByPublicAccessOrderByNumberStarDesc(Boolean.TRUE, PagableUtil.createPagable(0, sizeElements));
        } else {
            scheduleTravelEntityList = scheduleTravelRepository
                    .findByCityEntityNameAndPublicAccess(cityName, Boolean.TRUE, PagableUtil.createPagable(0, sizeElements));
        }
        return scheduleTravelEntityList.stream().map(this::scheduleEntityToDTO).collect(Collectors.toList());
    }

    @Transactional
    public List<ScheduleDTO> retrieveTravelSchedule(@NonNull List<Long> scheduleIds) {
        List<ScheduleTravelEntity> scheduleTravelEntityList = scheduleTravelRepository.findAllById(scheduleIds);
        return scheduleTravelEntityList.stream().map(this::scheduleEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> retrieveTravelScheduleByUserUID(String userUID) {
        return scheduleTravelRepository.findAllByUserOwnerUid(userUID)
                .stream().map(this::scheduleEntityToDTO).collect(Collectors.toList());
    }

    private ScheduleDTO scheduleEntityToDTO(ScheduleTravelEntity scheduleTravel) {
        return new ScheduleDTO(scheduleTravel);
    }

}
