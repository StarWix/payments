package com.starwix.services;

import com.starwix.entities.Commission;
import com.starwix.entities.CommissionList;
import com.starwix.entities.enums.Brand;
import com.starwix.entities.enums.Currency;
import com.starwix.exceptions.BrandNotSupportedException;
import com.starwix.exceptions.TransactionNotSupportedException;
import com.starwix.repositories.CommissionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by starwix on 26.9.16.
 */
public class CommissionServiceTest {
    @Mock
    private CommissionRepository commissionRepository;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private CommissionService commissionService;

    @Before
    public void before() {
        commissionService = new CommissionService(commissionRepository);
    }

    @Test
    public void shouldParseFile() {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource("commissions.xml").getFile());

        final List<Commission> commissions = new ArrayList<>();
        commissions.add(new Commission(1, Brand.MASTERCARD, Currency.USD, new BigDecimal("2.5")));
        commissions.add(new Commission(2, Brand.MASTERCARD, Currency.RUB, new BigDecimal("2")));
        commissions.add(new Commission(3, Brand.MASTERCARD, Currency.BYN, new BigDecimal("1.5")));
        commissions.add(new Commission(4, Brand.VISA, Currency.BYN, new BigDecimal("1.5")));

        final CommissionList commissionList = commissionService.parseFile(file);
        Assert.assertEquals(new CommissionList(commissions), commissionList);
    }

    @Test
    public void shouldCalc() throws TransactionNotSupportedException, BrandNotSupportedException {
        Mockito.when(commissionRepository.findByBrandAndCurrency(Brand.VISA, Currency.BYN)).thenReturn(Optional.of(new Commission(4, Brand.VISA, Currency.BYN, new BigDecimal("1.5"))));
        final BigDecimal commission = commissionService.calc("411111111111", Currency.BYN, new BigDecimal(1000));

        Assert.assertEquals(new BigDecimal("15.00"), commission);
    }

    @Test(expected = TransactionNotSupportedException.class)
    public void shouldCalcWithTransactionNotSupportedException() throws TransactionNotSupportedException, BrandNotSupportedException {
        Mockito.when(commissionRepository.findByBrandAndCurrency(Brand.VISA, Currency.BYN)).thenReturn(Optional.empty());
        commissionService.calc("411111111111", Currency.BYN, new BigDecimal(1000));
    }
}
