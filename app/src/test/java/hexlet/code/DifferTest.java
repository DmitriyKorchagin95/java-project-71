package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testGenerateWithAbsolutePaths() throws Exception {
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
    void testGenerateMirroredFileWithAbsolutePath() throws Exception {
        String actual = Differ.generate(filePath1, Path.of(filePath1).toAbsolutePath().toString());

        String expected = """
                {
                    follow: false
                    host: hexlet.io
                    proxy: 123.234.53.22
                    timeout: 50
                }""";

        assertEquals(expected, actual);
    }
}
