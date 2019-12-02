package com.nicetravel.nicetravel.repository;

import com.nicetravel.nicetravel.model.VoteScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteScheduleRepository extends JpaRepository<VoteScheduleEntity, Long> {

    long countAllByUserVoteUid(String userUID);
}
