package hexlet.code;

import hexlet.code.model.DiffEntry;
import hexlet.code.model.DiffStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public final class Differ {

    private Differ() {
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        var content1 = Files.readString(Path.of(filePath1));
        var content2 = Files.readString(Path.of(filePath2));
        var data1 = Parser.parse(content1);
        var data2 = Parser.parse(content2);
        var diffs = findDiffs(data1, data2);
        return Formatter.formatData(diffs, format);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    private static List<DiffEntry> findDiffs(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());
        List<DiffEntry> diffs = new ArrayList<>();

        for (String key : keys) {
            boolean inFirst = data1.containsKey(key);
            boolean inSecond = data2.containsKey(key);

            if (inFirst && !inSecond) {
                diffs.add(new DiffEntry(key, data1.get(key), null, DiffStatus.REMOVED));
            } else if (!inFirst && inSecond) {
                diffs.add(new DiffEntry(key, null, data2.get(key), DiffStatus.ADDED));
            } else {
                Object v1 = data1.get(key);
                Object v2 = data2.get(key);

                if (Objects.equals(v1, v2)) {
                    diffs.add(new DiffEntry(key, v1, v2, DiffStatus.UNCHANGED));
                } else {
                    diffs.add(new DiffEntry(key, v1, v2, DiffStatus.UPDATED));
                }
            }
        }

        return diffs;
    }
}
