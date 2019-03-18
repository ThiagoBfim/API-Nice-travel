package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_SCHEDULE_TRAVEL", schema = Constants.SCHEMA)
public class ScheduleTravel extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_SCHEDULE_TRAVEL";

    @Id
    @Column(name = "CO_SCHEDULE_TRAVEL")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Column(name = "NU_DIAS", nullable = false)
    private Integer numberDays;

    @Column(name = "VL_PRICE_TRAVEL") //Talvez seja calculado
    private BigDecimal priceOfTravel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleTravel")
    private List<ScheduleDay> scheduleDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_CITY", foreignKey = @ForeignKey(name = "FK_SCHEDULE_TRAVEL_TO_CITY"), nullable = false)
    private CityEntity cityEntity;

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

    public BigDecimal getPriceOfTravel() {
        return priceOfTravel;
    }

    public void setPriceOfTravel(BigDecimal priceOfTravel) {
        this.priceOfTravel = priceOfTravel;
    }

    public List<ScheduleDay> getScheduleDays() {
        return scheduleDays;
    }

    public void setScheduleDays(List<ScheduleDay> scheduleDays) {
        this.scheduleDays = scheduleDays;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }
}
