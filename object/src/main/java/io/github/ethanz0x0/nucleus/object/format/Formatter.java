package io.github.ethanz0x0.nucleus.object.format;

import io.github.ethanz0x0.nucleus.object.ListUtil;
import io.github.ethanz0x0.nucleus.object.StringUtil;

import java.util.List;
import java.util.logging.Logger;

public class Formatter {

    private static final Logger logger = Logger.getLogger(Formatter.class.getName());

    public static String format(String input, Format... formats) {
        if (StringUtil.isNullOrBlank(input) || formats == null) {
            return input;
        }
        for (Format format : formats) {
            try {
                input = format.format(input);
            } catch (Exception e) {
                logger.severe("Error applying format " + format.getClass().getSimpleName());
                e.printStackTrace();
            }
        }
        return input;
    }

    public static List<String> format(List<String> input, Format... formats) {
        if (ListUtil.isNullOrEmpty(input) || formats == null) {
            return input;
        }
        for (Format format : formats) {
            try {
                input = format.format(input);
            } catch (Exception e) {
                logger.severe("Error applying format " + format.getClass().getSimpleName());
                e.printStackTrace();
            }
        }
        return input;
    }

    private Formatter() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}