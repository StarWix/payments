package com.starwix.entities;

import com.starwix.entities.enums.Currency;
import com.starwix.entities.requests.TransactionRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by starwix on 21.9.16.
 */
public class TransactionStored extends TransactionRequest {
    private BigDecimal commission;
    private LocalDateTime createdDate;

    public TransactionStored(final CardInformation sender,
                             final CardInformation receiver,
                             final BigDecimal amount,
                             final Currency currency,
                             final BigDecimal commission,
                             final LocalDateTime createdDate) {
        super(sender, receiver, amount, currency);
        this.commission = commission;
        this.createdDate = createdDate;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "TransactionStored{" +
                "commission=" + commission +
                ", createdDate=" + createdDate +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionStored)) return false;
        if (!super.equals(o)) return false;

        TransactionStored that = (TransactionStored) o;

        if (!commission.equals(that.commission)) return false;
        return createdDate.equals(that.createdDate);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + commission.hashCode();
        result = 31 * result + createdDate.hashCode();
        return result;
    }

    public static TransactionStored valueOf(final TransactionRequest request,
                                            final BigDecimal commission,
                                            final LocalDateTime createdDate) {
        return new TransactionStored(
                request.getSender(),
                request.getReceiver(),
                request.getAmount(),
                request.getCurrency(),
                commission,
                createdDate
        );
    }
}
