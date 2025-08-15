package dev.ethanz0x0.nucleus.object.format.presets;

import dev.ethanz0x0.nucleus.object.StringUtil;
import dev.ethanz0x0.nucleus.object.format.Format;
import dev.ethanz0x0.nucleus.object.format.Formatter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OrderedEmptyPlaceholderFormat extends Format {

    public static OrderedEmptyPlaceholderFormat create(Object... replacers) {
        return new OrderedEmptyPlaceholderFormat(replacers);
    }

    private final NumericOrderedPlaceholderFormat placeholderFormat;

    private OrderedEmptyPlaceholderFormat(Object... replacers) {
        this.placeholderFormat = NumericOrderedPlaceholderFormat.builder()
                .placeholderSection("{@a}")
                .replacements(replacers)
                .build();
    }

    @Override
    public String format(String input) {
        if (StringUtil.isNullOrBlank(input)) {
            return nullToEmpty(input);
        }

        String formatted = handleBraces(input, new AtomicInteger());
        return Formatter.format(formatted, placeholderFormat);
    }

    @Override
    public List<String> format(List<String> input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        AtomicInteger index = new AtomicInteger();
        return input.stream()
                .map(s -> handleBraces(s, index))
                .map(s -> Formatter.format(s, placeholderFormat))
                .collect(Collectors.toList());
    }

    private String handleBraces(String input, AtomicInteger index) {
        StringBuilder result = new StringBuilder(input);

        int start = result.indexOf("{}");
        while (start != -1) {
            String replacement = String.format("{%d}", index.getAndIncrement());
            result.replace(start, start + 2, replacement);
            start = result.indexOf("{}", start + replacement.length());
        }

        return result.toString();
    }
}