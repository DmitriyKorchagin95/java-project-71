package hexlet.code;

import hexlet.code.model.DiffEntry;
import hexlet.code.model.DiffStatus;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public final class DiffFinder {

    private DiffFinder() {
    }

    public static List<DiffEntry> findDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());
        List<DiffEntry> diff = new ArrayList<>();

        for (String key : keys) {
            boolean inFirst = data1.containsKey(key);
            boolean inSecond = data2.containsKey(key);

            if (inFirst && !inSecond) {
                diff.add(new DiffEntry(key, data1.get(key), null, DiffStatus.REMOVED));
            } else if (!inFirst && inSecond) {
                diff.add(new DiffEntry(key, null, data2.get(key), DiffStatus.ADDED));
            } else {
                Object v1 = data1.get(key);
                Object v2 = data2.get(key);

                if (Objects.equals(v1, v2)) {
                    diff.add(new DiffEntry(key, v1, v2, DiffStatus.UNCHANGED));
                } else {
                    diff.add(new DiffEntry(key, v1, v2, DiffStatus.UPDATED));
                }
            }
        }

        return diff;
    }
}
