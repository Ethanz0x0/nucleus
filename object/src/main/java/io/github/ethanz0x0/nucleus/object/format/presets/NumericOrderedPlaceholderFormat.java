package io.github.ethanz0x0.nucleus.object.format.presets;

import io.github.ethanz0x0.nucleus.object.format.Format;
import io.github.ethanz0x0.nucleus.object.format.Formatter;

import java.util.List;

public class NumericOrderedPlaceholderFormat extends Format {

    public static Builder builder() {
        return new Builder();
    }

    private final PlaceholderFormat placeholderFormat;

    private NumericOrderedPlaceholderFormat(String placeholderSection, Object... replacers) {
        PlaceholderFormat.Builder builder = PlaceholderFormat.builder().placeholderSection(placeholderSection);
        int index = 0;
        for (Object replacer : replacers) {
            builder.append(String.valueOf(index), replacer);
            index ++;
        }
        placeholderFormat = builder.build();
    }

    @Override
    public String format(String input) {
        return Formatter.format(input, placeholderFormat);
    }

    @Override
    public List<String> format(List<String> input) {
        return Formatter.format(input, placeholderFormat);
    }

    public static class Builder {

        private String placeholderSection = "{@*}";
        private Object[] replacements = new Object[]{};

        private Builder() {
        }

        public Builder placeholderSection(String placeholderSection) {
            this.placeholderSection = placeholderSection;
            return this;
        }

        public Builder replacements(Object... replacements) {
            this.replacements = replacements;
            return this;
        }

        public NumericOrderedPlaceholderFormat build() {
            return new NumericOrderedPlaceholderFormat(placeholderSection, replacements);
        }
    }
}