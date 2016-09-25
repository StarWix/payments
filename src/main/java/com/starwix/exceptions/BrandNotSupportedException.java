package com.starwix.exceptions;

/**
 * Created by starwix on 21.9.16.
 */
public class BrandNotSupportedException extends Exception implements WebError {
    private final String number;

    public BrandNotSupportedException(final String number) {
        super("Brand is unsupported with card number = " + number);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String getWebMessage() {
        return "Ваш тип карты не поддерживается";
    }
}
