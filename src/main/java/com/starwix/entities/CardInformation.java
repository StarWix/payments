package com.starwix.entities;

/**
 * Created by starwix on 21.9.16.
 */
public class CardInformation {
    private String number;
    private String firstName;
    private String lastName;
    private Byte month;
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
