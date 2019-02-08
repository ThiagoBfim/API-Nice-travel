package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_CITY", schema = Constants.SCHEMA)
public class CityEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_CITY";

    @Id
    @Column(name = "CO_CITY")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Column(name = "DS_NAME", nullable = false, length = 300)
    private String name;

    @Column(name = "DS_COUNTRY", nullable = false, length = 300)
    private String country;

    @Column(name = "DS_STATE", nullable = false, length = 300)
    private String state;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CITY_TYPE", schema = Constants.SCHEMA,
            joinColumns = {@JoinColumn(name = "CO_CITY", nullable = true, updatable = false,
                    foreignKey = @ForeignKey(name = "FK_CITY_TO_TYPE"))},
            inverseJoinColumns = {@JoinColumn(name = "CO_TYPE", nullable = true, updatable = false,
                    foreignKey = @ForeignKey(name = "FK_TYPE_TO_CITY"))})
    private List<TypeCityEntity> typeCities;

    @Override
    public Long getCod() {
        return cod;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public List<TypeCityEntity> getTypeCities() {
        return typeCities;
    }

    public void setTypeCities(List<TypeCityEntity> typeCities) {
        this.typeCities = typeCities;
    }
}
