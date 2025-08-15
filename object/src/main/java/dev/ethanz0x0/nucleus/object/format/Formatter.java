package dev.ethanz0x0.nucleus.object.format;

import dev.ethanz0x0.nucleus.object.ListUtil;
import dev.ethanz0x0.nucleus.object.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Formatter {

    private static final Logger logger = LoggerFactory.getLogger(Formatter.class);

    public static String format(String input, Format... formats) {
        if (StringUtil.isNullOrBlank(input) || formats == null) {
            return input;
        }
        for (Format format : formats) {
            try {
                input = format.format(input);
            } catch (Exception e) {
                logger.error("Error applying format {}", format.getClass().getSimpleName(), e);
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
                logger.error("Error applying format {}", format.getClass().getSimpleName(), e);
            }
        }
        return input;
    }

    private Formatter() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}