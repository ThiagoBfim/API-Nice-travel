package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;

@Entity
@Table(
        name = "TB_USER",
        schema = Constants.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "UK_UID_FIREBASE", columnNames = "CO_UID_FIREBASE")}
)
public class UserEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_USER";

    @Id
    @Column(name = "CO_USER")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @Column(name = "DS_NAME", length = 300)
    private String name;

    @Column(name = "DS_EMAIL")
    private String email;

    @Column(name = "CO_UID_FIREBASE", nullable = false, updatable = false)
    private String uid;

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
        if (name != null) {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
