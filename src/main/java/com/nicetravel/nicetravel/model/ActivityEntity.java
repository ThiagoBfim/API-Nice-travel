package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.model.enuns.GenericEnumUserType;
import com.nicetravel.nicetravel.model.enuns.StyleActivity;
import com.nicetravel.nicetravel.util.Constants;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "TB_ACTIVITY", schema = Constants.SCHEMA)
public class ActivityEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_ACTIVITY";

    @Id
    @Column(name = "CO_ACTIVITY")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Column(name = "DS_NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "DS_DESCRIBE", nullable = false, length = 300)
    private String description;

    @Type(type = GenericEnumUserType.CLASS_NAME, parameters = {
            @org.hibernate.annotations.Parameter(name = "enumClass", value = StyleActivity.CLASS_NAME),
            @org.hibernate.annotations.Parameter(name = "identifierMethod", value = "getDescription"),
            @org.hibernate.annotations.Parameter(name = "valueOfMethod", value = "valueOfEnum")})
    @Column(name = "DS_TYPE_ACTIVITY", nullable = false, length = 50)
    private StyleActivity styleActivity;

    @Column(name = "VL_PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "DT_START", nullable = false)
    private LocalTime dtStart;

    @Column(name = "DT_END")
    private LocalTime dtEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_SCHEDULE_DAY", foreignKey = @ForeignKey(name = "FK_ACTIVITY_TO_SCHEDULE_DAY"), nullable = false)
    private ScheduleDayEntity scheduleDayEntity;


    public ActivityEntity duplicate(ScheduleDayEntity scheduleDayEntity) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setStyleActivity(styleActivity);
        activityEntity.setDtStart(dtStart);
        activityEntity.setDtEnd(dtEnd);
        activityEntity.setPrice(price);
        activityEntity.setName(name);
        activityEntity.setDescription(description);
        activityEntity.setScheduleDayEntity(scheduleDayEntity);
        return activityEntity;
    }

    @Override
    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StyleActivity getStyleActivity() {
        return styleActivity;
    }

    public void setStyleActivity(StyleActivity styleActivity) {
        this.styleActivity = styleActivity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalTime getDtStart() {
        return dtStart;
    }

    public void setDtStart(LocalTime dtStart) {
        this.dtStart = dtStart;
    }

    public LocalTime getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(LocalTime dtEnd) {
        this.dtEnd = dtEnd;
    }

    public ScheduleDayEntity getScheduleDayEntity() {
        return scheduleDayEntity;
    }

    public void setScheduleDayEntity(ScheduleDayEntity scheduleDayEntity) {
        this.scheduleDayEntity = scheduleDayEntity;
    }

}
