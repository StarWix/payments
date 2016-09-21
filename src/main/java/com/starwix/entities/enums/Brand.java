package com.starwix.entities.enums;

import com.starwix.exceptions.BrandNotSupportedException;
import us.fatehi.creditcardnumber.CardBrand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by starwix on 21.9.16.
 */
public enum Brand {
    MASTERCARD(CardBrand.MasterCard),
    VISA(CardBrand.Visa),
    AMERICAN_EXPRESS(CardBrand.AmericanExpress),
    DINERS_CLUB(CardBrand.DinersClub),
    DISCOVER(CardBrand.Discover),
    JSB(CardBrand.JCB);

    private static final Map<CardBrand, Brand> brandByCardBrand;

    static {
        brandByCardBrand = new HashMap<>();
        for (final Brand brand : Brand.values()) {
            brandByCardBrand.put(brand.cardBrand, brand);
        }
    }

    private final CardBrand cardBrand;

    Brand(final CardBrand cardBrand) {
        this.cardBrand = cardBrand;
    }

    public static Brand byNumber(final String number) throws BrandNotSupportedException {
        if (number == null) {
            throw new NullPointerException();
        }
        final Brand brand = brandByCardBrand.get(CardBrand.from(number));
        if (brand == null) {
            throw new BrandNotSupportedException(number);
        }
        return brand;
    }
}
