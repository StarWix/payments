package com.starwix.repositories;

import com.starwix.entities.requests.TransactionRequest;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by starwix on 21.9.16.
 */
public class TransactionRepository {
    private final DataSource dataSource;

    @Inject
    public TransactionRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Long save(final TransactionRequest request, final BigDecimal commission, final Date createdDate) {
        return null;
    }
}
