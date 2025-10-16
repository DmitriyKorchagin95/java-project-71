package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.Map;

public final class Formatter {

    private Formatter() {
    }

    public static String format(Map<String, Object> data1, Map<String, Object> data2, String format) {
        return switch (format) {
            case "stylish" -> Stylish.formattedToStylish(data1, data2);
            default -> throw new IllegalStateException("Unexpected format value: %s".formatted(format));
        };
    }
}
