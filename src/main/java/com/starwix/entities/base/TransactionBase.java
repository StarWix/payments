package com.starwix.entities.base;

import com.starwix.entities.CardInformation;
import com.starwix.entities.enums.Currency;

import java.math.BigDecimal;

/**
 * Created by starwix on 21.9.16.
 */
public interface TransactionBase {
    CardInformation getSender();
    CardInformation getReceiver();
    BigDecimal getAmount();
    Currency getCurrency();
}
