package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Differ.generate() tests")
class DifferTest {

    private static Path json1;
    private static Path json2;
    private static Path yml1;
    private static Path yml2;
    private static Path emptyJsonFile;
    private static Path emptyYmlFile;
    private static Path invalidFile;

    @BeforeAll
    static void setUp() {
        Path resourcesDir = Paths.get("src", "test", "resources");
        json1 = resourcesDir.resolve("json1.json");
        json2 = resourcesDir.resolve("json2.json");
        yml1 = resourcesDir.resolve("yml1.yml");
        yml2 = resourcesDir.resolve("yml2.yml");
        emptyJsonFile = resourcesDir.resolve("emptyjsonfile.json");
        emptyYmlFile = resourcesDir.resolve("emptyymlfile.yml");
        invalidFile = resourcesDir.resolve("invalid.json");
    }

    @Test
    @DisplayName("Basic diff between two json files")
    void testGenerateBasicJsonFormat() throws Exception {
        String actual = Differ.generate(json1.toString(), json2.toString());

        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Basic diff between two yml files")
    void testGenerateBasicYmlFormat() throws Exception {
        String actual = Differ.generate(yml1.toString(), yml2.toString());

        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Diff with absolute file paths")
    void testGenerateWithAbsolutePaths() throws Exception {
        String absolutePath1 = json1.toAbsolutePath().toString();
        String absolutePath2 = yml2.toAbsolutePath().toString();

        String actual = Differ.generate(absolutePath1, absolutePath2);

        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Identical json files produce no differences")
    void testGenerateIdenticalJsonFiles() throws Exception {
        String actual = Differ.generate(json1.toString(), json1.toString());

        String expected = """
                {
                    follow: false
                    host: hexlet.io
                    proxy: 123.234.53.22
                    timeout: 50
                }""";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Identical yml files produce no differences")
    void testGenerateIdenticalYmlFiles() throws Exception {
        String actual = Differ.generate(yml1.toString(), yml1.toString());

        String expected = """
                {
                    follow: false
                    host: hexlet.io
                    proxy: 123.234.53.22
                    timeout: 50
                }""";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Empty json file should cause an exception")
    void testGenerateEmptyJsonFile() {
        assertThrows(Exception.class, () ->
                Differ.generate(json1.toString(), emptyJsonFile.toString()));
    }

    @Test
    @DisplayName("Empty yml file should cause an exception")
    void testGenerateEmptyYmlFile() {
        assertThrows(Exception.class, () ->
                Differ.generate(yml1.toString(), emptyYmlFile.toString()));
    }

    @Test
    @DisplayName("Invalid file should cause an exception")
    void testGenerateInvalidFile() {
        assertThrows(Exception.class, () ->
                Differ.generate(invalidFile.toString(), invalidFile.toString()));
    }
}
