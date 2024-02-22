package com.skylab.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FormatValues {

    private static DecimalFormat amountFormat = new DecimalFormat("###,##0.000");
    private static DecimalFormat integerAmountFormat = new DecimalFormat("###,##0");

    public static String BigDecimalToAmountString(BigDecimal value) {
        if (value.stripTrailingZeros().scale() <= 0) {
            return integerAmountFormat.format(value);
        }
        return amountFormat.format(value);
    }

    public static String BigDecimalToMonetaryString(BigDecimal value) {
        return NumberFormat.getCurrencyInstance().format(value);
    }

    public static BigDecimal StringToBigDecimal(String value) {
        return new BigDecimal(value.replace(",", "."));
    }

    public static String BigDecimalToString(BigDecimal value) {
        return value.toString().replace(".", ",");
    }

    public static String mascaraTelefone(String telefone) {
        if (telefone.length() >= 10) {
            return telefone.replaceFirst("(\\d{2})(\\d{4,5})(\\d{4})", "($1) $2-$3");
        }
        return telefone;
    }

    public static String mascaraCpfCnpj(String cpfCnpj) {
        if (cpfCnpj.length() == 11) {
            return cpfCnpj.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return cpfCnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}
