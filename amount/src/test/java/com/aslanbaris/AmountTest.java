package com.aslanbaris;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.RoundingMode;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AmountTest {
    Locale localeTr = new Locale("tr", "TR");

    @Test
    void testAmountValue() {
        Amount amount = new Amount(5);
        assertEquals(amount.getValue(), 5);

        amount = new Amount(10);
        assertEquals(amount.getValue(), 10);

        amount.setValue(8);
        assertEquals(amount.getValue(), 8);
    }

    @Test
    void testAmountDefaultString() {
        Amount amount = new Amount(5);
        assertEquals(amount.toString(), "5.0");

        amount = new Amount(12);
        assertEquals(amount.toString(), "12.0");

        amount.setValue(20);
        assertEquals(amount.toString(), "20.0");

        amount.setValue(0);
        assertEquals(amount.toString(), "0.0");
    }

    @Test
    void testDefaultLocale() {
        Amount amount = new Amount(5);
        assertNotNull(amount.getLocale());
    }

    @Test
    void testDecimalCount() {
        Amount amount = new Amount(5);

        amount.setDecimalCount(2);
        assertEquals(amount.toString(), "5.00");

        amount.setDecimalCount(4);
        assertEquals(amount.toString(), "5.0000");

        amount.setValue(0);
        assertEquals(amount.toString(), "0.0000");
    }

    @Test
    void testDecimalCountBiggerThanValuesDecimalCount() {
        Amount amount = new Amount(0.19);
        amount.setDecimalCount(4);
        assertEquals(amount.toString(), "0.1900");
    }

    @Test
    void testDecimalCountSmallerThanValuesDecimalCount() {
        Amount amount = new Amount(0.981234);
        amount.setDecimalCount(4);
        assertEquals(amount.toString(), "0.9812");
    }

    @Test
    void testDecimalCountEqualToValuesDecimalCount() {
        Amount amount = new Amount(0.19);
        amount.setDecimalCount(2);
        assertEquals(amount.toString(), "0.19");
    }

    @Test
    void testDifferentLocale() {
        Amount amount = new Amount(2);
        amount.setLocale(localeTr);

        assertNotNull(amount.getLocale());
        assertEquals(amount.toString(), "2,0");

        amount.setDecimalCount(4);
        assertEquals(amount.toString(), "2,0000");
    }

    @Test
    void testGroupingFormat() {
        Amount amount = new Amount(1234);
        assertEquals(amount.toString(), "1,234.0");

        amount.setValue(123456);
        assertEquals(amount.toString(), "123,456.0");

        amount.setValue(1234567);
        assertEquals(amount.toString(), "1,234,567.0");
    }

    @Test
    void testGroupingFormatWithDifferentLocale() {
        Amount amount = new Amount(1234);
        amount.setLocale(localeTr);

        assertEquals(amount.toString(), "1.234,0");

        amount.setValue(123456);
        assertEquals(amount.toString(), "123.456,0");

        amount.setValue(1234567);
        assertEquals(amount.toString(), "1.234.567,0");
    }

    @Test
    void testGroupingWithDecimalFormat() {
        Amount amount = new Amount(1234);
        amount.setDecimalCount(4);
        assertEquals(amount.toString(), "1,234.0000");

        amount.setValue(1234.19);
        assertEquals(amount.toString(), "1,234.1900");

        amount.setValue(1234.12341234);
        assertEquals(amount.toString(), "1,234.1234");
    }

    @Test
    void testDefaultRoundingMode() {
        Amount amount = new Amount(0);
        assertEquals(amount.getRoundingMode(), RoundingMode.HALF_UP);
    }

    @Test
    @Disabled
    void testDefaultRoundingModeHalfDecimal() {
        Amount amount = new Amount(0.15);
        assertEquals(amount.toString(), "0.2");

        amount.setValue(-0.15);
        assertEquals(amount.toString(), "-0.2");
    }

    @Test
    void testDefaultRoundingModeUpDecimal() {
        Amount amount = new Amount(0.16);
        assertEquals(amount.toString(), "0.2");

        amount.setValue(-0.16);
        assertEquals(amount.toString(), "-0.2");
    }

    @Test
    void testDefaultRoundingModeDownDecimal() {
        Amount amount = new Amount(0.11);
        assertEquals(amount.toString(), "0.1");

        amount.setValue(-0.11);
        assertEquals(amount.toString(), "-0.1");
    }

    @Test
    void testCeilingHalfDecimal() {
        Amount amount = new Amount(0.15);
        amount.setRoundingMode(RoundingMode.CEILING);
        assertEquals(amount.toString(), "0.2");

        amount.setValue(-0.15);
        assertEquals(amount.toString(), "-0.1");
    }

    @Test
    void testCeilingUpDecimal() {
        Amount amount = new Amount(0.16);
        amount.setRoundingMode(RoundingMode.CEILING);
        assertEquals(amount.toString(), "0.2");

        amount.setValue(-0.16);
        assertEquals(amount.toString(), "-0.1");
    }

    @Test
    void testCeilingDownDecimal() {
        Amount amount = new Amount(0.11);
        amount.setRoundingMode(RoundingMode.CEILING);
        assertEquals(amount.toString(), "0.2");

        amount.setValue(-0.11);
        assertEquals(amount.toString(), "-0.1");
    }

    @Test
    void testFloorHalfDecimal() {
        Amount amount = new Amount(0.15);
        amount.setRoundingMode(RoundingMode.FLOOR);
        assertEquals(amount.toString(), "0.1");

        amount.setValue(-0.15);
        assertEquals(amount.toString(), "-0.2");
    }

    @Test
    void testFloorUpDecimal() {
        Amount amount = new Amount(0.16);
        amount.setRoundingMode(RoundingMode.FLOOR);
        assertEquals(amount.toString(), "0.1");

        amount.setValue(-0.16);
        assertEquals(amount.toString(), "-0.2");
    }

    @Test
    void testFloorDownDecimal() {
        Amount amount = new Amount(0.11);
        amount.setRoundingMode(RoundingMode.FLOOR);
        assertEquals(amount.toString(), "0.1");

        amount.setValue(-0.11);
        assertEquals(amount.toString(), "-0.2");
    }

    @Test
    void testDecimalCountZero() {
        Amount amount = new Amount(0.12);
        amount.setDecimalCount(0);
        assertEquals(amount.toString(), "0");
    }

    @Test
    void testDecimalCountZeroWithoutDecimal() {
        Amount amount = new Amount(12);
        amount.setDecimalCount(0);
        assertEquals(amount.toString(), "12");
    }

    @Test
    void testDecimalCountZeroHalfDecimal() {
        Amount amount = new Amount(1234.55);
        amount.setDecimalCount(0);
        assertEquals(amount.toString(), "1,235");
    }

    @Test
    void testDecimalCountZeroUpDecimal() {
        Amount amount = new Amount(1234.99);
        amount.setDecimalCount(0);
        assertEquals(amount.toString(), "1,235");
    }

    @Test
    void testDecimalCountZeroDownDecimal() {
        Amount amount = new Amount(1234.12);
        amount.setDecimalCount(0);
        assertEquals(amount.toString(), "1,234");
    }
}
