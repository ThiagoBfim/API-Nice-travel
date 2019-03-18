package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.model.enuns.GenericEnumUserType;
import com.nicetravel.nicetravel.model.enuns.StyleActivity;
import com.nicetravel.nicetravel.util.Constants;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ACTIVITY", schema = Constants.SCHEMA)
public class Activity extends BaseEntity {

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
    private BigDecimal priceOfHotel;

    @Column(name = "DT_START", nullable = false)
    private LocalDateTime dtStart;

    @Column(name = "DT_END")
    private LocalDateTime dtEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_SCHEDULE_DAY", foreignKey = @ForeignKey(name = "FK_ACTIVITY_TO_SCHEDULE_DAY"), nullable = false)
    private ScheduleDay scheduleDay;

    @Override
    public Long getCod() {
        return cod;
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

    public BigDecimal getPriceOfHotel() {
        return priceOfHotel;
    }

    public void setPriceOfHotel(BigDecimal priceOfHotel) {
        this.priceOfHotel = priceOfHotel;
    }

    public LocalDateTime getDtStart() {
        return dtStart;
    }

    public void setDtStart(LocalDateTime dtStart) {
        this.dtStart = dtStart;
    }

    public LocalDateTime getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(LocalDateTime dtEnd) {
        this.dtEnd = dtEnd;
    }

    public ScheduleDay getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(ScheduleDay scheduleDay) {
        this.scheduleDay = scheduleDay;
    }
}
