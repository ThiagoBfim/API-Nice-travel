package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TB_CITY", schema = Constants.SCHEMA
)
public class CityEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_CITY";

    @Id
    @Column(name = "CO_CITY")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Column(name = "DS_NAME", nullable = false, length = 300, unique = true)//FIXME UK está errada.
    private String name;

    @Column(name = "DS_LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "DS_LONGITUDE", nullable = false)
    private Double longitude;

    @Column(name = "CO_PLACE_ID", nullable = false)
    private String placeID;

    @Column(name = "TX_PHOTO", nullable = false, length = 300)
    private String photoLink;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TB_CITY_TO_TYPE", schema = Constants.SCHEMA,
            joinColumns = {@JoinColumn(name = "CO_CITY", nullable = false, updatable = false,
                    foreignKey = @ForeignKey(name = "FK_CITY_TO_TYPE"))},
            inverseJoinColumns = {@JoinColumn(name = "CO_TYPE", nullable = false, updatable = false,
                    foreignKey = @ForeignKey(name = "FK_TYPE_TO_CITY"))},
            uniqueConstraints = @UniqueConstraint(name = "UK_CITY_TO_ENTITY", columnNames = {"CO_CITY", "CO_TYPE"}))
    private Set<TypeCityEntity> typeCities;

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Set<TypeCityEntity> getTypeCities() {
        return typeCities;
    }

    public void setTypeCities(Set<TypeCityEntity> typeCities) {
        this.typeCities = typeCities;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }
}
