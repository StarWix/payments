package com.starwix.controller.api;

import com.starwix.entities.Transaction;
import com.starwix.entities.requests.TransactionRequest;
import com.starwix.exceptions.BrandNotSupportedException;
import com.starwix.exceptions.TransactionUnsupportedException;
import com.starwix.services.TransactionService;
import org.jooby.Err;
import org.jooby.mvc.Body;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

import javax.inject.Inject;

/**
 * Created by starwix on 18.9.16.
 */
@Path("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Inject
    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @POST
    public Transaction post(@Body final TransactionRequest transactionRequest) {
        try {
            return transactionService.create(transactionRequest);
        } catch (TransactionUnsupportedException|BrandNotSupportedException e) {
            throw new Err(404);
        }
    }
}
