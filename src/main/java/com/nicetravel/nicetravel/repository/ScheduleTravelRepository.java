package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.ScheduleTravelEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleTravelRepository extends JpaRepository<ScheduleTravelEntity, Long> {

    List<ScheduleTravelEntity> findByCityEntityNameAndPublicAccess(String cityName, Boolean publicAccess, Pageable pageable);

    List<ScheduleTravelEntity> findAllByUserOwnerUid(String userUID);
}
