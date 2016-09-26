package com.starwix.services;

import com.starwix.entities.Transaction;
import com.starwix.entities.requests.TransactionRequest;
import com.starwix.exceptions.BrandNotSupportedException;
import com.starwix.exceptions.TransactionNotSupportedException;
import com.starwix.repositories.TransactionRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by starwix on 18.9.16.
 */
public class TransactionService {
    private final Clock clock;
    private final TransactionRepository transactionRepository;
    private final CommissionService commissionService;

    @Inject
    public TransactionService(final Clock clock,
                              final TransactionRepository transactionRepository,
                              final CommissionService commissionService) {
        this.clock = clock.withZone(ZoneOffset.UTC);
        this.transactionRepository = transactionRepository;
        this.commissionService = commissionService;
    }

    /**
     * Move money.
     *
     * @return createdDate of transaction.
     */
    private LocalDateTime moveMoney(final TransactionRequest transactionRequest, final BigDecimal commission) {
        // do something.
        return LocalDateTime.now(clock);
    }

    public Transaction create(final TransactionRequest request) throws TransactionNotSupportedException, BrandNotSupportedException {
        final BigDecimal commission = commissionService.calc(request.getSender().getNumber(), request.getCurrency(), request.getAmount());
        final LocalDateTime createdDate = moveMoney(request, commission);
        final Long id = transactionRepository.save(request, commission, createdDate);
        return Transaction.valueOf(id, request, commission, createdDate);
    }
}
