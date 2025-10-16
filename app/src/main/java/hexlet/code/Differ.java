package hexlet.code;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {

    private Differ() {
    }

    // 1. Read file
    // 2. Parse data
    // 3. Choose format
    // 4. Format data
    // 5. Output data
    public static String generate(String filePath1, String filePath2) throws IOException {
        var dataOfFile1 = Parser.parseFile(filePath1);
        var dataOfFile2 = Parser.parseFile(filePath2);

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
