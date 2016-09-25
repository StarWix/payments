package com.starwix.controller.api;

import com.starwix.entities.Commission;
import com.starwix.entities.enums.Currency;
import com.starwix.exceptions.BrandNotSupportedException;
import com.starwix.exceptions.TransactionNotSupportedException;
import com.starwix.services.CommissionService;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by starwix on 18.9.16.
 */
@Path("/api/commissions")
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

    // Если делать RESTful сервис, то метод должен быть get,
    // Но передавать данные карты по методу get не секьюрно, т.к данные легко могут попасть в логи.
    //@GET
    //@Path("/currency/:currency/number/:number/amount/:amount")
    @POST
    @Path("/calc")
    public BigDecimal calc(final String number, final Currency currency, final BigDecimal amount) throws TransactionNotSupportedException, BrandNotSupportedException {
        return commissionService.calc(number, currency, amount);
    }
}
