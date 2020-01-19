package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;

@Entity
@Table(name = "TB_PHOTO_CITY", schema = Constants.SCHEMA)
public class PhotoCityEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_PHOTO_CITY";

    @Id
    @Column(name = "CO_PHOTO_CITY")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Column(name = "TX_PHOTO", nullable = false, length = 1000)
    private String photoLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_CITY", foreignKey = @ForeignKey(name = "FK_PHOTO_CITY_TO_CITY"), nullable = false)
    private CityEntity city;

    @Override
    public Long getCod() {
        return cod;
    }

    public PhotoCityEntity setCod(Long cod) {
        this.cod = cod;
        return this;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public PhotoCityEntity setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
        return this;
    }

    public CityEntity getCity() {
        return city;
    }

    public PhotoCityEntity setCity(CityEntity cityEntity) {
        this.city = cityEntity;
        return this;
    }
}
