package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityEntity,Long> {
}
