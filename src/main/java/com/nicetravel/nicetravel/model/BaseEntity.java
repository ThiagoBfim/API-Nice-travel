package com.nicetravel.nicetravel.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseEntity implements Serializable {

    public abstract Long getCod();

    @Override
    public String toString() {
        return getClass().getName() +
                "[id = " + getCod() + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelEntity that = (TravelEntity) o;
        return getCod().equals(that.getCod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod());
    }
}
