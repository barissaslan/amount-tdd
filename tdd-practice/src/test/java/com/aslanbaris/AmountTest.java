package com.aslanbaris;


import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AmountTest {

    @Test
    void testAmountValue() {
        Amount amount = new Amount(5);
        assertEquals(amount.value, 5);

        amount = new Amount(10);
        assertEquals(amount.value, 10);
    }

    @Test
    void testAmountDefaultString() {
        Amount amount = new Amount(5);
        assertEquals(amount.toString(), "5.0");

        amount = new Amount(12);
        assertEquals(amount.toString(), "12.0");
    }

    @Test
    void testDefaultLocale() {
        Amount amount = new Amount(5);
        assertNotNull(amount.locale);
    }

    @Test
    void testDecimalCount() {
        Amount amount = new Amount(5);

        amount.decimalCount = 2;
        assertEquals(amount.toString(), "5.00");

        amount.decimalCount = 5;
        assertEquals(amount.toString(), "5.00000");
    }

    @Test
    void testDifferentLocale() {
        Locale locale = new Locale("tr", "TR");
        Amount amount = new Amount(2);
        amount.locale = locale;

        assertNotNull(amount.locale);
        assertEquals(amount.toString(), "2,0");

        amount.decimalCount = 4;
        assertEquals(amount.toString(), "2,0000");
    }
}
