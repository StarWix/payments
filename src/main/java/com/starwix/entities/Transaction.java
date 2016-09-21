package com.starwix.entities;

import com.starwix.entities.base.TransactionBase;
import com.starwix.entities.enums.Currency;
import com.starwix.entities.requests.TransactionRequest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by starwix on 21.9.16.
 */
public class Transaction implements TransactionBase {
    private Long id;
    private CardInformation sender;
    private CardInformation receiver;
    private BigDecimal amount;
    private Currency currency;
    private BigDecimal commission;
    private Date createdDate;

    public Transaction(final Long id,
                       final CardInformation sender,
                       final CardInformation receiver,
                       final BigDecimal amount,
                       final Currency currency,
                       final BigDecimal commission,
                       final Date createdDate) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.currency = currency;
        this.commission = commission;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    @Override
    public CardInformation getSender() {
        return sender;
    }

    @Override
    public CardInformation getReceiver() {
        return receiver;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                ", currency=" + currency +
                ", commission=" + commission +
                ", createdDate=" + createdDate +
                '}';
    }

    public static Transaction valueOf(final Long id,
                                      final TransactionRequest request,
                                      final BigDecimal commission,
                                      final Date createdDate) {
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
