package com.nicetravel.nicetravel.service.scheduleday.persist;

import com.nicetravel.nicetravel.dto.ScheduleDayDTO;
import com.nicetravel.nicetravel.model.ScheduleDayEntity;
import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import com.nicetravel.nicetravel.repository.ScheduleDayRepository;
import com.nicetravel.nicetravel.repository.ScheduleTravelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScheduleDayImplService extends AbstractScheduleDayService {

    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    @Autowired
    private ScheduleTravelRepository scheduleRepository;

    @Override
    public void deleteById(Long scheduleDayId) {
        updateDays(scheduleDayId);
        scheduleDayRepository.deleteById(scheduleDayId);
    }

    private void updateDays(Long scheduleDayId) {
        ScheduleDayEntity scheduleDay = scheduleDayRepository.getOne(scheduleDayId);
        List<ScheduleDayEntity> schedulesDays = scheduleDayRepository.findAllByScheduleTravelEntityCodOrderByDay(scheduleDay.getScheduleTravelEntity().getCod());

        schedulesDays
                .stream()
                .filter(d -> d.getDay() > scheduleDay.getDay())
                .forEach(d -> d.setDay(d.getDay() - 1));

        scheduleDayRepository.saveAll(schedulesDays);

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

    @Override
    public void reorder(Long scheduleDayIdFrom, Long scheduleDayIdTo) {
        ScheduleDayEntity scheduleDayFrom = scheduleDayRepository.getOne(scheduleDayIdFrom);
        ScheduleDayEntity scheduleDayTo = scheduleDayRepository.getOne(scheduleDayIdTo);
        int dayTo = scheduleDayTo.getDay();
        int dayFrom = scheduleDayFrom.getDay();
        scheduleDayTo.setDay(dayFrom);
        scheduleDayRepository.save(scheduleDayTo);
        scheduleDayFrom.setDay(dayTo);
        scheduleDayRepository.save(scheduleDayFrom);
    }
}
