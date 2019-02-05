package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;

@Entity
@Table(name = "TB_TYPE_CITY", schema = Constants.SCHEMA)
public class TypeCityEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_TYPE_CITY";

    @Id
    @Column(name = "CO_TYPE_CITY")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Column(name = "DS_DESCRIPTION", nullable = false, length = 300)
    private String description;

    @Override
    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
