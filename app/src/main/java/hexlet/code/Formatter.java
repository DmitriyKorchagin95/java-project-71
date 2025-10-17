package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.model.DiffEntry;

import java.util.List;

public final class Formatter {

    private Formatter() {
    }

    public static String formatData(List<DiffEntry> diffs, String format) {
        return switch (format) {
            case "stylish" -> Stylish.formatToStylish(diffs);
            case "plain" -> Plain.formatToPlain(diffs);
            default -> throw new IllegalStateException("Unexpected format value: %s".formatted(format));
        };
    }
}
