package com.nicetravel.nicetravel.service.scheduleday.persist;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.repository.ScheduleDayRepository;
import com.nicetravel.nicetravel.repository.ScheduleTravelRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleDayImplService extends AbstractScheduleDayService {

    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    @Autowired
    private ScheduleTravelRepository scheduleRepository;

    @Override
    public void removeById(Long scheduleDayId) {
        scheduleDayRepository.deleteById(scheduleDayId);
    }

    @Override
    public ScheduleDayEntity saveScheduleDay(Long scheduleId) {
        ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
        ScheduleTravelEntity scheduleTravel = scheduleRepository.getOne(scheduleId);
        scheduleDayEntity.setScheduleTravelEntity(scheduleTravel);
        scheduleDayEntity.setDay(scheduleTravel.getScheduleDayEntities().size() + 1);
        return scheduleDayRepository.save(scheduleDayEntity);
    }

    @Override
    public ScheduleDayDTO createScheduleDayDTO(ScheduleDayEntity scheduleDay) {
        return new ScheduleDayDTO()
                .setId(scheduleDay.getCod())
                .setDay(scheduleDay.getDay())
                .setPriceDay(scheduleDay.getPriceDay());
    }
}
