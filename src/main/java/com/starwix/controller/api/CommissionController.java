package com.starwix.controller.api;

import com.starwix.entities.enums.Currency;
import com.starwix.services.CommissionService;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

import javax.inject.Inject;
import java.math.BigDecimal;

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
    public String findAll() {
        return commissionService.findAll().toString();
    }

    @GET
    @Path("/calc")
    public String calc(final String brand, final Currency currency, final BigDecimal amount) {
        return commissionService.calc(brand, currency, amount).toString();
    }
}
