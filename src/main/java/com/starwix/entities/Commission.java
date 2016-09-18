package com.starwix.entities;

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
    private String brand;
    private Currency currency;
    private BigDecimal value;

    public Commission() {
    }

    public Commission(final int id, final String brand, final Currency currency, final BigDecimal value) {
        this.id = id;
        this.brand = brand;
        this.currency = currency;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
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
}
