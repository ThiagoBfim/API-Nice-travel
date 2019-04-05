package com.nicetravel.nicetravel.service.travel.retrieve;

import com.nicetravel.nicetravel.dto.ScheduleDTO;
import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
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
    public List<ScheduleDTO> getScheduleByCityName(@NonNull String cityName, @NonNull Integer sizeElements) {
        List<ScheduleTravelEntity> scheduleTravelEntityList = scheduleTravelRepository
                .findByCityEntityNameAndPublicAccess(cityName, Boolean.TRUE, PagableUtil.createPagable(0, sizeElements));
        return scheduleTravelEntityList.stream().map(this::scheduleEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDayDTO> getScheduleDays(@NonNull Long scheduleId) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }

    @Transactional
    public List<ScheduleDTO> retrieveTravelSchedule(@NonNull List<Long> scheduleIds) {
        throw new UnsupportedOperationException("HAVE TO BE IMPLEMENTED");
    }

    private ScheduleDTO scheduleEntityToDTO(ScheduleTravelEntity scheduleTravel) {
        return new ScheduleDTO()
                .setPriceFinal(scheduleTravel.getPriceFinal())
                .setScheduleCod(scheduleTravel.getCod())
                .setImageUrl(scheduleTravel.getCityImageUrl())
                .setNameCity(scheduleTravel.getCityName())
                .setQtdDays(scheduleTravel.getNumberDays());
    }
}
