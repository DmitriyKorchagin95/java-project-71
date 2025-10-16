package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class Stylish {

    private Stylish() {
    }

    public static String formattedToStylish(Map<String, Object> dataOfFile1, Map<String, Object> dataOfFile2) {

        Set<String> keys = new TreeSet<>();
        keys.addAll(dataOfFile1.keySet());
        keys.addAll(dataOfFile2.keySet());
        StringBuilder sb = new StringBuilder("{\n");

        for (String key : keys) {
            boolean inFirstFile = dataOfFile1.containsKey(key);
            boolean inSecondFile = dataOfFile2.containsKey(key);

            if (inFirstFile && !inSecondFile) {
                appendLine(sb, "-", key, dataOfFile1.get(key));
            } else if (!inFirstFile && inSecondFile) {
                appendLine(sb, "+", key, dataOfFile2.get(key));
            } else if (inFirstFile) {
                Object v1 = dataOfFile1.get(key);
                Object v2 = dataOfFile2.get(key);

                if (Objects.equals(v1, v2)) {
                    appendLine(sb, " ", key, v2);
                } else {
                    appendLine(sb, "-", key, v1);
                    appendLine(sb, "+", key, v2);
                }
            }
        }

        sb.append("}");
        return sb.toString();
    }

    private static void appendLine(StringBuilder sb, String sign, String key, Object value) {
        sb.append("  ")
                .append(sign)
                .append(" ")
                .append(key)
                .append(": ")
                .append(value)
                .append("\n");
    }
}
