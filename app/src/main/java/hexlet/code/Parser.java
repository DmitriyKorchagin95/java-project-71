package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public final class Parser {

    private Parser() {
    }

    public static Map<String, Object> parse(String content, String dataFormat) throws IOException {
        final ObjectMapper mapper = switch (dataFormat) {
            case "json" -> new ObjectMapper();
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IllegalArgumentException("Unexpected data format value: %s".formatted(dataFormat));
        };

        return mapper.readValue(content, new TypeReference<>() {
        });
    }
}
