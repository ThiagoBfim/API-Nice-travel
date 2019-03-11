package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<TravelEntity, Long> {

}
