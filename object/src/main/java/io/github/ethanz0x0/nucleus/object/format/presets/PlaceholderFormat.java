package io.github.ethanz0x0.nucleus.object.format.presets;

import io.github.ethanz0x0.nucleus.object.StringUtil;
import io.github.ethanz0x0.nucleus.object.format.Format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PlaceholderFormat extends Format {

    public static Builder builder() {
        return new Builder();
    }

    private final String placeholderSection;
    private final Map<String, Object> replacementTable;

    private PlaceholderFormat(String placeholderSection, Map<String, Object> replacementTable) {
        if (StringUtil.isNullOrBlank(placeholderSection)) {
            throw new IllegalArgumentException("placeholderSection cannot be null or blank");
        }

        if (!placeholderSection.contains("@*")) {
            throw new IllegalArgumentException("placeholderSection must contain @* as placeholder key");
        }

        this.placeholderSection = placeholderSection;
        this.replacementTable = replacementTable;
    }

    @Override
    public String format(String input) {
        if (StringUtil.isNullOrBlank(input)){
            return nullToEmpty(input);
        }

        if (replacementTable.isEmpty()) {
            return input;
        }

        String startSymbol;
        String endSymbol;

        try {
            startSymbol = placeholderSection.substring(0, placeholderSection.indexOf("@*"));
            endSymbol = placeholderSection.substring(placeholderSection.indexOf("@*") + 2);
        } catch (StringIndexOutOfBoundsException e) {
            startSymbol = "{";
            endSymbol = "}";
        }

        Pattern pattern = Pattern.compile(Pattern.quote(startSymbol) + "(.*?)" + Pattern.quote(endSymbol));
        Matcher matcher = pattern.matcher(input);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String placeholder = matcher.group(1);
            matcher.appendReplacement(result, replacementTable.getOrDefault(placeholder, "").toString());
        }
        matcher.appendTail(result);
        return result.toString();
    }

    @Override
    public List<String> format(List<String> input) {
        return input.stream().map(this::format).collect(Collectors.toList());
    }

    public static class Builder {

        private String placeholderSection = "{@*}";
        private final Map<String, Object> replacementTable = new HashMap<>();

        private Builder() {
        }

        public Builder placeholderSection(String placeholderSection) {
            this.placeholderSection = placeholderSection;
            return this;
        }

        public Builder append(String placeholder, Object replacement) {
            replacementTable.put(placeholder, replacement);
            return this;
        }

        public PlaceholderFormat build() {
            return new PlaceholderFormat(placeholderSection, replacementTable);
        }
    }
}
