package hexlet.code;

import hexlet.code.model.DiffEntry;
import hexlet.code.model.DiffStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public final class Differ {

    private Differ() {
    }

    // 1. Read file
    // 2. Parse data
    // 3. Find diff
    // 4. Format data
    // 5. Output data
    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        var data1 = Parser.parseFile(filePath1);
        var data2 = Parser.parseFile(filePath2);
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

        return Formatter.formatData(diffs, format);
    }
}
