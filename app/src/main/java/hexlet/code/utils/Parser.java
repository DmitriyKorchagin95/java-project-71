package hexlet.code.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public final class Parser {

    private Parser() {
    }

    public static Map<String, Object> parseFile(String filePath) throws IOException {
        ObjectMapper mapper;
        String content = FileReader.readFile(filePath);

        if (content.startsWith("{")) {
            mapper = new ObjectMapper();
        } else {
            mapper = new ObjectMapper(new YAMLFactory());
        }

        return mapper.readValue(content, new TypeReference<>() {
        });

    }
}
