package hexlet.code.formatters;

import hexlet.code.model.DiffEntry;
import hexlet.code.model.DiffStatus;

import java.util.List;
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
        var  stringifyOldValue = diff.oldValue() instanceof List<?> ? "[complex value]" : diff.oldValue();
        var  stringifyNewValue = diff.newValue() instanceof List<?> ? "[complex value]" : diff.newValue();


        return switch (diff.status()) {
            case REMOVED -> String.format("Property '%s' was removed.", diff.key());
            case ADDED -> String.format("Property '%s' was added with value: '%s'", diff.key(), stringifyOldValue);
            case UPDATED -> String.format("Property '%s' was updated. From %s to '%s'",
                    diff.key(),
                    stringifyOldValue,
                    stringifyNewValue
            );
            default -> throw new IllegalStateException("Unexpected status value: %s".formatted(diff.status()));
        };
    }
}
