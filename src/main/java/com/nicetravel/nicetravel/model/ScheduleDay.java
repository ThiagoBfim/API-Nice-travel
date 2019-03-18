package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_SCHEDULE_DAY", schema = Constants.SCHEMA)
public class ScheduleDay extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_SCHEDULE_DAY";

    @Id
    @Column(name = "CO_SCHEDULE_DAY")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleDay")
    private List<Activity> activities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_SCHEDULE_TRAVEL", foreignKey = @ForeignKey(name = "FK_SCHEDULE_DAY_TO_SCHEDULE_TRAVEL"), nullable = false)
    private ScheduleTravel scheduleTravel;

    @Override
    public Long getCod() {
        return cod;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public ScheduleTravel getScheduleTravel() {
        return scheduleTravel;
    }

    public void setScheduleTravel(ScheduleTravel scheduleTravel) {
        this.scheduleTravel = scheduleTravel;
    }
}
