package com.starwix.services;

import com.starwix.entities.CardInformation;
import com.starwix.entities.Transaction;
import com.starwix.entities.enums.Currency;
import com.starwix.entities.requests.TransactionRequest;
import com.starwix.exceptions.BrandNotSupportedException;
import com.starwix.exceptions.TransactionNotSupportedException;
import com.starwix.repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by starwix on 26.9.16.
 */
public class TransactionServiceTest {
    @Mock
    private CommissionService commissionService;
    @Mock
    private TransactionRepository transactionRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private TransactionService transactionService;

    @Before
    public void before() {
        final Clock clock = Clock.fixed(Instant.parse("2016-12-01T00:15:30.00Z"), ZoneOffset.UTC);

        transactionService = new TransactionService(clock, transactionRepository, commissionService);
    }

    @Test
    public void create() throws TransactionNotSupportedException, BrandNotSupportedException {
        final CardInformation sender = new CardInformation("411111111", "name1", "surname1", (byte)1, (short)2017);
        final CardInformation receiver = new CardInformation("411111112", "name2", "surname2", (byte)2, (short)2018);
        final Transaction excepted = new Transaction(1L, sender, receiver, new BigDecimal(1000), Currency.BYN,
                new BigDecimal("15.00"), LocalDateTime.parse("2016-12-01T00:15:30"));
        final TransactionRequest transactionRequest = new TransactionRequest(sender, receiver, new BigDecimal(1000), Currency.BYN);

        Mockito.when(commissionService.calc(excepted.getSender().getNumber(), Currency.BYN, new BigDecimal(1000))).thenReturn(new BigDecimal("15.00"));
        Mockito.when(transactionRepository.save(transactionRequest, new BigDecimal("15.00"), excepted.getCreatedDate())).thenReturn(1L);

        final Transaction transaction = transactionService.create(transactionRequest);

        Assert.assertEquals(excepted, transaction);
    }
}
