package com.starwix.exceptions;

import com.starwix.entities.enums.Brand;
import com.starwix.entities.enums.Currency;

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
    public String getWebMessage() {
        return "Транзакция карты " + brand + " с валютой " + currency + " не поддерживается.";
    }
}
