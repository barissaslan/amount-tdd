package com.aslanbaris;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Amount {
    public double value;
    public int decimalCount = 1;
    public Locale locale = Locale.US;

    public Amount(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);

        String decimalFormat = new String(new char[decimalCount]).replace("\0", "0");

        return new DecimalFormat(",###." + decimalFormat, decimalFormatSymbols).format(value);
    }
}
