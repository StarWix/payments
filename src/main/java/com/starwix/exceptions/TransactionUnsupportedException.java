package com.starwix.exceptions;

import com.starwix.entities.enums.Brand;
import com.starwix.entities.enums.Currency;

/**
 * Created by starwix on 21.9.16.
 */
public class TransactionUnsupportedException extends Exception {
    private final Brand brand;
    private final Currency currency;

    public TransactionUnsupportedException(final Brand brand, final Currency currency) {
        super("Transaction is unsupported with Brand = " + brand + " and Currency = " + currency);
        this.brand = brand;
        this.currency = currency;
    }

    public Brand getCardBrand() {
        return brand;
    }

    public Currency getCurrency() {
        return currency;
    }
}
