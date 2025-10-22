package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public final class Parser {

    private Parser() {
    }

    public static Map<String, Object> parse(String content) throws IOException {
        ObjectMapper mapper;

        if (content.startsWith("{")) {
            mapper = new ObjectMapper();
        } else {
            mapper = new ObjectMapper(new YAMLFactory());
        }

        return mapper.readValue(content, new TypeReference<>() {
        });
    }
}
