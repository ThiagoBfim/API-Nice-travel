package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "TB_SCHEDULE_DAY", schema = Constants.SCHEMA)
public class ScheduleDayEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_SCHEDULE_DAY";

    @Id
    @Column(name = "CO_SCHEDULE_DAY")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Column(name = "NU_DAY", nullable = false)
    private Integer day;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleDayEntity", cascade = CascadeType.ALL)
    private List<ActivityEntity> activities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_SCHEDULE_TRAVEL", foreignKey = @ForeignKey(name = "FK_SCHEDULE_DAY_TO_SCHEDULE_TRAVEL"), nullable = false)
    private ScheduleTravelEntity scheduleTravelEntity;

    public ScheduleDayEntity duplicate(ScheduleTravelEntity scheduleTravelEntity) {
        ScheduleDayEntity scheduleDayEntity = new ScheduleDayEntity();
        scheduleDayEntity.setDay(day);
        scheduleDayEntity.setScheduleTravelEntity(scheduleTravelEntity);
        scheduleDayEntity.setActivities(activities
                .stream()
                .map(act -> act.duplicate(scheduleDayEntity))
                .collect(Collectors.toList()));
        return scheduleDayEntity;
    }

    @Override
    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public List<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }

    public ScheduleTravelEntity getScheduleTravelEntity() {
        return scheduleTravelEntity;
    }

    public void setScheduleTravelEntity(ScheduleTravelEntity scheduleTravelEntity) {
        this.scheduleTravelEntity = scheduleTravelEntity;
    }

    public BigDecimal getPriceDay() {
        if (CollectionUtils.isEmpty(activities)) {
            return BigDecimal.ZERO;
        }
        return activities.stream()
                .filter(a -> a.getPrice() != null)
                .map(ActivityEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
