package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.model.enuns.GenericEnumUserType;
import com.nicetravel.nicetravel.model.enuns.StyleTravel;
import com.nicetravel.nicetravel.util.Constants;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_TRAVEL", schema = Constants.SCHEMA)
public class TravelEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_TRAVEL";

    @Id
    @Column(name = "CO_TRAVEL")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Type(type = GenericEnumUserType.CLASS_NAME, parameters = {
            @Parameter(name = "enumClass", value = StyleTravel.CLASS_NAME),
            @Parameter(name = "identifierMethod", value = "getAbbreviation"),
            @Parameter(name = "valueOfMethod", value = "valueOfEnum")})
    @Column(name = "TP_STYLE_TRAVEL", nullable = false, length = 1)
    private StyleTravel styleTravel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_CITY", foreignKey = @ForeignKey(name = "FK_TRAVEL_TO_CITY"), nullable = false)
    private CityEntity cityEntity;

    @Column(name = "VL_PRICE", nullable = false)
    private BigDecimal price;


    @Override
    public Long getCod() {
        return cod;
    }

    public StyleTravel getStyleTravel() {
        return styleTravel;
    }

    public void setStyleTravel(StyleTravel styleTravel) {
        this.styleTravel = styleTravel;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}

