package hexlet.code.formatters;

import hexlet.code.model.DiffEntry;

import java.util.List;

public final class Stylish {

    private Stylish() {
    }

    public static String formatToStylish(List<DiffEntry> diffs) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffEntry diff : diffs) {
            switch (diff.status()) {
                case REMOVED -> result.append(String.format("  - %s: %s%n", diff.key(), diff.oldValue()));
                case ADDED -> result.append(String.format("  + %s: %s%n", diff.key(), diff.newValue()));
                case UPDATED -> {
                    result.append(String.format("  - %s: %s%n", diff.key(), diff.oldValue()));
                    result.append(String.format("  + %s: %s%n", diff.key(), diff.newValue()));
                }
                case UNCHANGED -> result.append(String.format("    %s: %s%n", diff.key(), diff.oldValue()));
                default -> throw new IllegalStateException("Unexpected status value: %s".formatted(diff.status()));
            }
        }
        result.append("}");
        return result.toString();
    }
}
