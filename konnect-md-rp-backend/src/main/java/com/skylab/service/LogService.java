package com.skylab.service;

import com.skylab.KonnectMdRp;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogService {

    @Getter
    private static final Logger LOGGER = LoggerFactory.getLogger(KonnectMdRp.class);

    public static void log(String... log) {
        for (String s : log) {
            LOGGER.debug(s);
        }
    }


}
