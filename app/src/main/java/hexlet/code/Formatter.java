package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.model.DiffEntry;

import java.util.List;

public final class Formatter {

    private Formatter() {
    }

    public static String formatData(List<DiffEntry> diffs, String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.formatToStylish(diffs);
            case "plain" -> Plain.formatToPlain(diffs);
            case "json" -> Json.formatToJson(diffs);
            default -> throw new IllegalStateException("Unexpected format value: %s".formatted(format));
        };
    }
}
