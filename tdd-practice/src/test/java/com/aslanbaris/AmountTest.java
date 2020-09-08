package com.aslanbaris;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AmountTest {
    Locale localeTr = new Locale("tr", "TR");

    @Test
    void testAmountValue() {
        Amount amount = new Amount(5);
        assertEquals(amount.value, 5);

        amount = new Amount(10);
        assertEquals(amount.value, 10);

        amount.value = 8;
        assertEquals(amount.value, 8);
    }

    @Test
    void testAmountDefaultString() {
        Amount amount = new Amount(5);
        assertEquals(amount.toString(), "5.0");

        amount = new Amount(12);
        assertEquals(amount.toString(), "12.0");

        amount.value = 20;
        assertEquals(amount.toString(), "20.0");
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

        amount.decimalCount = 4;
        assertEquals(amount.toString(), "5.0000");

        amount.value = 0;
        assertEquals(amount.toString(), "0.0000");
    }

    @Test
    void testDecimalCountBiggerThanValuesDecimalCount() {
        Amount amount = new Amount(0.19);
        amount.decimalCount = 4;
        assertEquals(amount.toString(), "0.1900");
    }

    @Test
    void testDecimalCountSmallerThanValuesDecimalCount() {
        Amount amount = new Amount(0.981234);
        amount.decimalCount = 4;
        assertEquals(amount.toString(), "0.9812");
    }

    @Test
    void testDecimalCountEqualToValuesDecimalCount() {
        Amount amount = new Amount(0.19);
        amount.decimalCount = 2;
        assertEquals(amount.toString(), "0.19");
    }

    @Test
    @Disabled
    void testDecimalCountSmallerThanValuesDecimalCountWithRounding() {
        Amount amount = new Amount(0.19999);
        amount.decimalCount = 4;
        assertEquals(amount.toString(), "0.1999");
    }

    @Test
    void testDifferentLocale() {
        Amount amount = new Amount(2);
        amount.locale = localeTr;

        assertNotNull(amount.locale);
        assertEquals(amount.toString(), "2,0");

        amount.decimalCount = 4;
        assertEquals(amount.toString(), "2,0000");
    }

    @Test
    void testGroupingFormat() {
        Amount amount = new Amount(1234);
        assertEquals(amount.toString(), "1,234.0");

        amount.value = 123456;
        assertEquals(amount.toString(), "123,456.0");

        amount.value = 1234567;
        assertEquals(amount.toString(), "1,234,567.0");
    }

    @Test
    void testGroupingFormatWithDifferentLocale() {
        Amount amount = new Amount(1234);
        amount.locale = localeTr;

        assertEquals(amount.toString(), "1.234,0");

        amount.value = 123456;
        assertEquals(amount.toString(), "123.456,0");

        amount.value = 1234567;
        assertEquals(amount.toString(), "1.234.567,0");
    }

    @Test
    void testGroupingWithDecimalFormat() {
        Amount amount = new Amount(1234);
        amount.decimalCount = 4;
        assertEquals(amount.toString(), "1,234.0000");

        amount.value = 1234.19;
        assertEquals(amount.toString(), "1,234.1900");

        amount.value = 1234.12341234;
        assertEquals(amount.toString(), "1,234.1234");
    }
}
