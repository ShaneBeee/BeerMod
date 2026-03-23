package com.github.shanebeee.beer.api.utils;

import com.github.shanebeee.beer.mod.Beer;

public class Utils {

    public static void log(String format, Object... args) {
        if (args.length > 0) {
            format = String.format(format, args);
        }
        Beer.LOGGER.info(format);
    }

    public static void warn(String format, Object... args) {
        if (args.length > 0) {
            format = String.format(format, args);
        }
        Beer.LOGGER.warn(format);
    }

    public static void error(String format, Object... args) {
        if (args.length > 0) {
            format = String.format(format, args);
        }
        Beer.LOGGER.error(format);
    }

}
