package hexlet.code;

import hexlet.code.utils.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    @DisplayName("Parses JSON file into a Map")
    void testParseJson(@TempDir Path tmpDir) throws Exception {
        Path jsonFile = tmpDir.resolve("tmp.json");
        String json = """
                {
                  "host": "localhost",
                  "port": 8080,
                  "debug": true,
                  "numbers": [1, 2, 3, 4]
                }
                """;
        Files.writeString(jsonFile, json, Charset.defaultCharset());
        Map<String, Object> result = Parser.parseFile(jsonFile.toString());

        assertNotNull(result);
        assertEquals("localhost", result.get("host"));
        assertEquals(8080, (result.get("port")));
        assertEquals(Boolean.TRUE, result.get("debug"));
        assertEquals(List.of(1, 2, 3, 4), result.get("numbers"));
    }

    @Test
    @DisplayName("Parses YAML file into a Map")
    void testParseYaml(@TempDir Path tmpDir) throws Exception {
        Path yamlFile = tmpDir.resolve("tmp.yaml");
        String yaml = """
                host: localhost
                port: 8080
                debug: true
                numbers:
                - 1
                - 2
                - 3
                - 4
                """;
        Files.writeString(yamlFile, yaml, Charset.defaultCharset());
        Map<String, Object> result = Parser.parseFile(yamlFile.toString());

        assertNotNull(result);
        assertEquals("localhost", result.get("host"));
        assertEquals(8080, (result.get("port")));
        assertEquals(Boolean.TRUE, result.get("debug"));
        assertEquals(List.of(1, 2, 3, 4), result.get("numbers"));
    }

    @Test
    @DisplayName("Missing file causes IOException")
    void testMissingFile() {
        Path missing = Path.of("src", "test", "resources", "missing.json");
        assertFalse(Files.exists(missing));
        assertThrows(IOException.class, () -> Parser.parseFile(missing.toString()));
    }
}
