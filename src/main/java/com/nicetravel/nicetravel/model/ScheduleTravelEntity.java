package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_SCHEDULE_TRAVEL", schema = Constants.SCHEMA)
public class ScheduleTravelEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_SCHEDULE_TRAVEL";

    @Id
    @Column(name = "CO_SCHEDULE_TRAVEL")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleTravelEntity", cascade = CascadeType.ALL)
    private List<ScheduleDayEntity> scheduleDayEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_CITY", foreignKey = @ForeignKey(name = "FK_SCHEDULE_TRAVEL_TO_CITY"), nullable = false)
    private CityEntity cityEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_USER", foreignKey = @ForeignKey(name = "FK_SCHEDULE_TRAVEL_TO_USER"), nullable = false)
    private UserEntity userOwner;

    @Column(name = "ST_PUBLIC_ACCESS", nullable = false)
    private Boolean publicAccess;

    @Column(name = "NU_STARS", nullable = false)
    private Integer numberStar;


    @PrePersist
    public void prePersist(){
        if(numberStar == null){
            numberStar = 0;
        }
    }
    @Override
    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
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

    public String getCityImageUrl() {
        return cityEntity.getPhotoLink();
    }

    public String getFormattedAdress() {
        return cityEntity.getFormattedAddress();
    }

    public UserEntity getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserEntity userOwner) {
        this.userOwner = userOwner;
    }

    public BigDecimal getPriceFinal() {
        if (CollectionUtils.isEmpty(scheduleDayEntities)) {
            return BigDecimal.ZERO;
        }
        return scheduleDayEntities.stream()
                .filter(a -> a.getPriceDay() != null)
                .map(ScheduleDayEntity::getPriceDay)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
