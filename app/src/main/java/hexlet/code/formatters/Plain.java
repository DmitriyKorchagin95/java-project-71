package hexlet.code.formatters;

import hexlet.code.model.DiffEntry;
import hexlet.code.model.DiffStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Plain {

    private Plain() {
    }

    public static String formatToPlain(List<DiffEntry> diffs) {
        return diffs.stream()
                .filter(diff -> diff.status() != DiffStatus.UNCHANGED)
                .map(Plain::formatLine)
                .collect(Collectors.joining("\n"));
    }

    private static String formatLine(DiffEntry diff) {
        return switch (diff.status()) {
            case REMOVED -> String.format("Property '%s' was removed", diff.key());
            case ADDED -> String.format("Property '%s' was added with value: %s",
                    diff.key(), plainValue(diff.newValue()));
            case UPDATED -> String.format("Property '%s' was updated. From %s to %s",
                    diff.key(), plainValue(diff.oldValue()), plainValue(diff.newValue()));
            default -> throw new IllegalStateException("Unexpected status value: " + diff.status());
        };
    }

    private static String plainValue(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return String.format("'%s'", value);
        }

        return String.valueOf(value);
    }
}
