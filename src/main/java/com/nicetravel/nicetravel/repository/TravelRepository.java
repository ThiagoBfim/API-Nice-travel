package com.nicetravel.nicetravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nicetravel.nicetravel.model.TravelEntity;



public interface TravelRepository extends JpaRepository<TravelEntity, Long> {
    
}
