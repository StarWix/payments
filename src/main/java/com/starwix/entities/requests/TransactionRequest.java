package com.starwix.entities.requests;

import com.starwix.entities.CardInformation;
import com.starwix.entities.enums.Currency;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by starwix on 21.9.16.
 */
public class TransactionRequest {
    @NotNull(message = "Укажите данные отправителя")
    @Valid
    private CardInformation sender;
    @NotNull(message = "Укажите данные получателя")
    @Valid
    private CardInformation receiver;
    @NotNull(message = "Укажите сумму перевода")
    @Min(value = 1, message = "Сумма должна быть больше 1")
    private BigDecimal amount;
    @NotNull(message = "Выберите валюту")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionRequest)) return false;

        TransactionRequest that = (TransactionRequest) o;

        if (!sender.equals(that.sender)) return false;
        if (!receiver.equals(that.receiver)) return false;
        if (!amount.equals(that.amount)) return false;
        return currency == that.currency;

    }

    @Override
    public int hashCode() {
        int result = sender.hashCode();
        result = 31 * result + receiver.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }
}
