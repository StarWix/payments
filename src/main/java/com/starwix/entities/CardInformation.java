package com.starwix.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by starwix on 21.9.16.
 */
public class CardInformation {
    @NotBlank(message = "Укажите номер карты")
    @Pattern(regexp = "\\d{6,}", message = "Карта должна содержать только цифры")
    private String number;
    @NotBlank(message = "Укажите имя")
    private String firstName;
    @NotBlank(message = "Укажите фамилию")
    private String lastName;
    @NotNull(message = "Укажите месяц до которого действует карта")
    @Min(value = 1, message = "Месяц должен быть в диапозоне от 1 до 12")
    @Max(value = 12, message = "Месяц должен быть в диапозоне от 1 до 12")
    private Byte month;
    @NotNull(message = "Укажите год до которого действует карта")
    @Min(value = 2016, message = "Год должен быть в диапозоне от 2016 до 2036")
    @Max(value = 2036, message = "Год должен быть в диапозоне от 2016 до 2036")
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
