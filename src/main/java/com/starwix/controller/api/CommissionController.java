package com.starwix.controller.api;

import com.starwix.entities.Commission;
import com.starwix.entities.enums.Currency;
import com.starwix.exceptions.BrandNotSupportedException;
import com.starwix.exceptions.TransactionUnsupportedException;
import com.starwix.services.CommissionService;
import org.jooby.Err;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by starwix on 18.9.16.
 */
@Path("/api/commission")
public class CommissionController {
    private final CommissionService commissionService;

    @Inject
    public CommissionController(final CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @GET
    public List<Commission> findAll() {
        return commissionService.findAll();
    }

    @GET
    @Path("/calc")
    public BigDecimal calc(final String number, final Currency currency, final BigDecimal amount) throws FileNotFoundException {
        try {
            return commissionService.calc(number, currency, amount);
        } catch (final TransactionUnsupportedException|BrandNotSupportedException e) {
            throw new Err(404);
        }
    }
}
