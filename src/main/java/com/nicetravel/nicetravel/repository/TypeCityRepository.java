package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.TypeCityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeCityRepository extends JpaRepository<TypeCityEntity, Long> {

    Optional<TypeCityEntity> findByDescription(String description);
}
