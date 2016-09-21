package com.starwix.entities;

import com.starwix.entities.enums.Currency;
import com.starwix.entities.requests.TransactionRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by starwix on 21.9.16.
 */
public class Transaction extends TransactionStored {
    private Long id;

    public Transaction(final Long id,
                       final CardInformation sender,
                       final CardInformation receiver,
                       final BigDecimal amount,
                       final Currency currency,
                       final BigDecimal commission,
                       final LocalDateTime createdDate) {
        super(sender, receiver, amount, currency, commission, createdDate);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                "} " + super.toString();
    }

    public static Transaction valueOf(final Long id,
                                      final TransactionRequest request,
                                      final BigDecimal commission,
                                      final LocalDateTime createdDate) {
        return new Transaction(
                id,
                request.getSender(),
                request.getReceiver(),
                request.getAmount(),
                request.getCurrency(),
                commission,
                createdDate
        );
    }
}
