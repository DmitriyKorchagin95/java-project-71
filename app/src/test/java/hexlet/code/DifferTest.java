package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {
    private static String filePath1;
    private static String filePath2;

    @BeforeAll
    static void beforeAll() {
        filePath1 = Path.of("src/test/resources/file1.json").toString();
        filePath2 = Path.of("src/test/resources/file2.json").toString();
    }

    @Test
    void testGenerate() throws Exception {
        String actual = Differ.generate(filePath1, filePath2);

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
    void testGenerateWithAbsolutePath() throws Exception {
        String absolutePath1 = Path.of(filePath1).toAbsolutePath().toString();
        String absolutePath2 = Path.of(filePath2).toAbsolutePath().toString();

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
    void testGenerateWithEmptyFile() throws Exception {
        String emptyFile = "src/test/resources/empty.json";
        assertThrows(Exception.class, () -> Differ.generate(emptyFile, emptyFile));
    }

    @Test
    void testGenerateWithInvalidFilePath() {
        String invalidFile = "src/test/resources/invalid.json";
        assertThrows(Exception.class, () -> Differ.generate(invalidFile, invalidFile));
    }
}
