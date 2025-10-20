package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.model.DiffEntry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Json {

    private Json() {
    }

    public static String formatToJson(List<DiffEntry> diffs) throws JsonProcessingException {

        Map<String, Object> resultForJson = new LinkedHashMap<>();

        for (DiffEntry diff : diffs) {
            Map<String, Object> value = new LinkedHashMap<>();
            value.put("status", diff.status());
            value.put("value", diff.newValue());
            resultForJson.put(diff.key(), value);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(resultForJson);
    }
}
