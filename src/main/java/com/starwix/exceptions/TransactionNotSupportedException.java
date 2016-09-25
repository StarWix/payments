package com.starwix.exceptions;

import com.starwix.entities.enums.Brand;
import com.starwix.entities.enums.Currency;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by starwix on 21.9.16.
 */
public class TransactionNotSupportedException extends Exception implements WebError {
    private final Brand brand;
    private final Currency currency;

    public TransactionNotSupportedException(final Brand brand, final Currency currency) {
        super("Transaction is unsupported with Brand = " + brand + " and Currency = " + currency);
        this.brand = brand;
        this.currency = currency;
    }

    public Brand getBrand() {
        return brand;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String getError() {
        return "transactionNotSupported";
    }

    @Override
    public Map<String, Object> getArgs() {
        final Map<String, Object> args = new HashMap<>();
        args.put("brand", brand);
        args.put("currency", currency);
        return args;
    }
}
