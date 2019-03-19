package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByPlaceID(String placeID);
}
