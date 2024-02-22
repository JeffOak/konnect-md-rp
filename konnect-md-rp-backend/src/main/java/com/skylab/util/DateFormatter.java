package com.skylab.util;

import lombok.Getter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Jefferson Carvalho
 */
public class DateFormatter {

    @Getter
    private static final DateFormat dateWcFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
