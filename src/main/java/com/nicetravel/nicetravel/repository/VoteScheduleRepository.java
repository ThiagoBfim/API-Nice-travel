package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.UserEntity;
import com.nicetravel.nicetravel.model.VoteScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface VoteScheduleRepository extends JpaRepository<VoteScheduleEntity, Long> {

    long countAllByUserVoteAndScheduleTravelEntity_Cod(UserEntity user, Long scheduleCod);

    @Modifying
    long deleteAllByScheduleTravelEntity_Cod(Long scheduleCod);
}
