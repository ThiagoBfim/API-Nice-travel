package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity,Long> {

    List<ActivityEntity> findAllByScheduleDayEntityCod(Long scheduleDayId);
}
