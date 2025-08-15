package dev.ethanz0x0.nucleus.object.format.presets;

import dev.ethanz0x0.nucleus.object.format.Format;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumberFormattedFormat extends Format {

    public static NumberFormattedFormat create(String numberFormat) {
        return new NumberFormattedFormat(numberFormat);
    }

    private final String format;

    private NumberFormattedFormat(String format) {
        this.format = format;
    }

    @Override
    public String format(String input) {
        return formatNumbersInString(input, format);
    }

    @Override
    public List<String> format(List<String> input) {
        return input.stream().map(this::format).collect(Collectors.toList());
    }

    private String formatNumbersInString(String input, String format) {
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(input);
        StringBuffer result = new StringBuffer();

        DecimalFormat decimalFormat = new DecimalFormat(format);

        while (matcher.find()) {
            String number = matcher.group();
            try {
                double parsedNumber = Double.parseDouble(number);
                String formattedNumber = decimalFormat.format(parsedNumber);
                matcher.appendReplacement(result, formattedNumber);
            } catch (NumberFormatException e) {
                matcher.appendReplacement(result, number);
            }
        }
        matcher.appendTail(result);

        return result.toString();
    }
}