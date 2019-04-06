package com.nicetravel.nicetravel.service.travel.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.repository.ScheduleDayRepository;
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

    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    @Override
    public List<ScheduleDTO> getScheduleByCityName(@NonNull String cityName, @NonNull Integer sizeElements) {
        List<ScheduleTravelEntity> scheduleTravelEntityList = scheduleTravelRepository
                .findByCityEntityNameAndPublicAccess(cityName, Boolean.TRUE, PagableUtil.createPagable(0, sizeElements));
        return scheduleTravelEntityList.stream().map(this::scheduleEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDayDTO> getScheduleDays(@NonNull Long scheduleId) {
        List<ScheduleDayEntity> scheduleDayEntities = scheduleDayRepository.findAllByScheduleTravelEntityCod(scheduleId);
        return scheduleDayEntities.stream().map(this::scheduleDayToDTO).collect(Collectors.toList());
    }

    @Transactional
    public List<ScheduleDTO> retrieveTravelSchedule(@NonNull List<Long> scheduleIds) {
        List<ScheduleTravelEntity> scheduleTravelEntityList = scheduleTravelRepository.findAllById(scheduleIds);
        return scheduleTravelEntityList.stream().map(this::scheduleEntityToDTO).collect(Collectors.toList());
    }

    private ScheduleDTO scheduleEntityToDTO(ScheduleTravelEntity scheduleTravel) {
        return new ScheduleDTO()
                .setPriceFinal(scheduleTravel.getPriceFinal())
                .setScheduleCod(scheduleTravel.getCod())
                .setImageUrl(scheduleTravel.getCityImageUrl())
                .setNameCity(scheduleTravel.getCityName())
                .setQtdDays(scheduleTravel.getNumberDays());
    }

    private ScheduleDayDTO scheduleDayToDTO(ScheduleDayEntity scheduleDay) {
        return new ScheduleDayDTO()
                .setDay(scheduleDay.getDay())
                .setPriceDay(scheduleDay.getPriceDay());
    }
}
