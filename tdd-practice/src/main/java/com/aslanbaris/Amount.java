package com.aslanbaris;

import lombok.Getter;
import lombok.Setter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Getter
@Setter
public class Amount {

    private static final char GROUPING_SEPERATOR = ',';
    private static final char DECIMAL_SEPERATOR = '.';

    private double value;
    private int decimalCount = 1;
    private Locale locale = Locale.US;
    private RoundingMode roundingMode = RoundingMode.HALF_UP;

    public Amount(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return toFormatted();
    }

    private String toFormatted() {
        DecimalFormat decimalFormat = buildDecimalFormat();
        return decimalFormat.format(value);
    }

    private DecimalFormat buildDecimalFormat() {
        String format = generateFormat();
        DecimalFormat decimalFormat = new DecimalFormat(format, new DecimalFormatSymbols(locale));
        decimalFormat.setRoundingMode(roundingMode);
        return decimalFormat;
    }

    private String generateFormat() {
        return GROUPING_SEPERATOR + "##0" + checkAndGenerateDecimalFormat();
    }

    private String checkAndGenerateDecimalFormat() {
        if (decimalCount > 0) {
            return DECIMAL_SEPERATOR + new String(new char[decimalCount]).replace("\0", "0");
        }

        return "";
    }

}