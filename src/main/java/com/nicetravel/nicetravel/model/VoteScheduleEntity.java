package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;

@Entity
@Table(name = "TB_VOTE_SCHEDULE", schema = Constants.SCHEMA)
public class VoteScheduleEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_VOTE_SCHEDULE";

    @Id
    @Column(name = "CO_VOTE_SCHEDULE")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_USER", foreignKey = @ForeignKey(name = "FK_VOTE_SCHEDULE_TO_USER"), nullable = false)
    private UserEntity userVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_SCHEDULE_TRAVEL", foreignKey = @ForeignKey(name = "FK_VOTE_SCHEDULE_TO_SCHEDULE_TRAVEL"), nullable = false)
    private ScheduleTravelEntity scheduleTravelEntity;

    @Override
    public Long getCod() {
        return cod;
    }
    public void setCod(Long cod) {
        this.cod = cod;
    }

    public UserEntity getUserVote() {
        return userVote;
    }

    public VoteScheduleEntity setUserVote(UserEntity userVote) {
        this.userVote = userVote;
        return this;
    }

    public ScheduleTravelEntity getScheduleTravelEntity() {
        return scheduleTravelEntity;
    }

    public VoteScheduleEntity setScheduleTravelEntity(ScheduleTravelEntity scheduleTravelEntity) {
        this.scheduleTravelEntity = scheduleTravelEntity;
        return this;
    }
}
