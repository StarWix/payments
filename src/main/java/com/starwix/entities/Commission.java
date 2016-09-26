package com.starwix.entities;

import com.starwix.entities.enums.Brand;
import com.starwix.entities.enums.Currency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;

/**
 * Created by starwix on 18.9.16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Commission {
    @XmlAttribute
    private int id;
    private Brand brand;
    private Currency currency;
    private BigDecimal value;

    public Commission() {
    }

    public Commission(final int id, final Brand brand, final Currency currency, final BigDecimal value) {
        this.id = id;
        this.brand = brand;
        this.currency = currency;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Commission{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", currency=" + currency +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commission that = (Commission) o;

        if (id != that.id) return false;
        if (brand != that.brand) return false;
        if (currency != that.currency) return false;
        return value.equals(that.value);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + brand.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
