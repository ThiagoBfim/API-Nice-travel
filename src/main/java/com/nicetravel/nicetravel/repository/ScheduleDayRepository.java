package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.ScheduleDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleDayRepository extends JpaRepository<ScheduleDayEntity, Long> {

    List<ScheduleDayEntity> findAllByScheduleTravelEntityCod(Long scheduleTravelCod);

}
