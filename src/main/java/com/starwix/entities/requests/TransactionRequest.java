package com.starwix.entities.requests;

import com.starwix.entities.CardInformation;
import com.starwix.entities.enums.Currency;

import java.math.BigDecimal;

/**
 * Created by starwix on 21.9.16.
 */
public class TransactionRequest {
    private CardInformation sender;
    private CardInformation receiver;
    private BigDecimal amount;
    private Currency currency;

    public TransactionRequest(final CardInformation sender,
                              final CardInformation receiver,
                              final BigDecimal amount,
                              final Currency currency) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.currency = currency;
    }

    public CardInformation getSender() {
        return sender;
    }

    public CardInformation getReceiver() {
        return receiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String toString() {
        return "TransactionRequest{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
