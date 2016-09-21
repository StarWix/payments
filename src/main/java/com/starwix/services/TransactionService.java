package com.starwix.services;

import com.starwix.entities.Transaction;
import com.starwix.entities.requests.TransactionRequest;
import com.starwix.exceptions.BrandNotSupportedException;
import com.starwix.exceptions.TransactionUnsupportedException;
import com.starwix.repositories.TransactionRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by starwix on 18.9.16.
 */
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CommissionService commissionService;

    @Inject
    public TransactionService(final TransactionRepository transactionRepository,
                              final CommissionService commissionService) {
        this.transactionRepository = transactionRepository;
        this.commissionService = commissionService;
    }

    /**
     * Move money.
     *
     * @return createdDate of transaction.
     */
    private Date moveMoney(final TransactionRequest transactionRequest) {
        // do something.
        return new Date();
    }

    public Transaction create(final TransactionRequest request) throws TransactionUnsupportedException, BrandNotSupportedException {
        final BigDecimal commission = commissionService.calc(request.getSender().getNumber(), request.getCurrency(), request.getAmount());
        final Date createdDate = moveMoney(request);
        final Long id = transactionRepository.save(request, commission, createdDate);
        return Transaction.valueOf(id, request, commission, createdDate);
    }
}
