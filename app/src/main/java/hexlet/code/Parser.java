package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class Parser {
    public static Map<String, Object> parseFile(String filePath) throws IOException {
        ObjectMapper mapper;
        String content = readFile(filePath);

        if (content.startsWith("{")) {
            mapper = new ObjectMapper();
        } else {
            mapper = new ObjectMapper(new YAMLFactory());
        }

        return mapper.readValue(content, new TypeReference<>() {
        });

    }

    private static String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath), Charset.defaultCharset());
    }
}
