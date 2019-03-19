package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleTravelRepository extends JpaRepository<ScheduleTravelEntity, Long> {

    List<ScheduleTravelEntity> findByCityEntityName(String cityName);
}
