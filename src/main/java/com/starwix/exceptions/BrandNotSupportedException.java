package com.starwix.exceptions;

import java.util.HashMap;
import java.util.Map;

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
    public String getError() {
        return "brandNotSupported";
    }

    @Override
    public Map<String, Object> getArgs() {
        return new HashMap<>();
    }
}
