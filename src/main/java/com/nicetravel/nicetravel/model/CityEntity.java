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

    @Column(name = "DS_LATITUDE", nullable = false, length = 64)
    private String latitude;

    @Column(name = "DS_LONGITUDE", nullable = false, length = 64)
    private String longitude;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CITY_TYPE", schema = Constants.SCHEMA,
            joinColumns = {@JoinColumn(name = "CO_CITY", nullable = false, updatable = false,
                    foreignKey = @ForeignKey(name = "FK_CITY_TO_TYPE"))},
            inverseJoinColumns = {@JoinColumn(name = "CO_TYPE", nullable = false, updatable = false,
                    foreignKey = @ForeignKey(name = "FK_TYPE_TO_CITY"))})
    private List<TypeCityEntity> typeCities;

    @Column(name = "TX_PHOTO", nullable = false, length = 300)
    private String photo_link;

    @Override
    public Long getCod() {
        return cod;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getPhoto_link() {
        return photo_link;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }
}
