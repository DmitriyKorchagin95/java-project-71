package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {

    private Differ() {
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        var dataOfFile1 = getData(readFile(filePath1));
        var dataOfFile2 = getData(readFile(filePath2));

        Set<String> keys = new TreeSet<>();
        keys.addAll(dataOfFile1.keySet());
        keys.addAll(dataOfFile2.keySet());

        StringBuilder sb = new StringBuilder("{\n");

        for (String key : keys) {
            boolean inFirst = dataOfFile1.containsKey(key);
            boolean inSecond = dataOfFile2.containsKey(key);

            if (inFirst && !inSecond) {
                appendLine(sb, "-", key, dataOfFile1.get(key));
            } else if (!inFirst && inSecond) {
                appendLine(sb, "+", key, dataOfFile2.get(key));
            } else if (inFirst) {
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

    private static Map<String, Object> getData(String content) throws JsonProcessingException {
        return new ObjectMapper().readValue(content, new TypeReference<>() {
        });
    }

    private static String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
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
