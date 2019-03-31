package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_SCHEDULE_TRAVEL", schema = Constants.SCHEMA)
public class ScheduleTravelEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_SCHEDULE_TRAVEL";

    @Id
    @Column(name = "CO_SCHEDULE_TRAVEL")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Column(name = "NU_DIAS", nullable = false)
    private Integer numberDays;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleTravelEntity")
    private List<ScheduleDayEntity> scheduleDayEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_CITY", foreignKey = @ForeignKey(name = "FK_SCHEDULE_TRAVEL_TO_CITY"), nullable = false)
    private CityEntity cityEntity;

    @Column(name = "ST_PUBLIC_ACCESS", nullable = false)
    private Boolean publicAccess;

    @Column(name = "NU_STARS", nullable = false)
    private Integer numberStar = 0;

    @Override
    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public Integer getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(Integer numberDays) {
        this.numberDays = numberDays;
    }

    public List<ScheduleDayEntity> getScheduleDayEntities() {
        return scheduleDayEntities;
    }

    public void setScheduleDayEntities(List<ScheduleDayEntity> scheduleDayEntities) {
        this.scheduleDayEntities = scheduleDayEntities;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public Boolean getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(Boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    public Integer getNumberStar() {
        return numberStar;
    }

    public void setNumberStar(Integer numberStar) {
        this.numberStar = numberStar;
    }
}
