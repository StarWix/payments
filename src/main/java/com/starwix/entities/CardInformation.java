package com.starwix.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by starwix on 21.9.16.
 */
public class CardInformation {
    @NotBlank
    private String number;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Min(1)
    @Max(12)
    private Byte month;
    @NotNull
    @Min(2000)
    @Max(2100)
    private Short year;

    public CardInformation(String number, String firstName, String lastName, Byte month, Short year) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.month = month;
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Byte getMonth() {
        return month;
    }

    public Short getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "CardInformation{" +
                "number='" + number + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
