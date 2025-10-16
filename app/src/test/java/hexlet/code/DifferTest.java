package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private static String expectedBasic;
    private static String expectedIdentical;

    @BeforeAll
    static void setUp() throws Exception {
        expectedBasic = readFixture("ExpectedBasic.txt");
        expectedIdentical = readFixture("ExpectedIdentical.txt");
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @ParameterizedTest
    @CsvSource({"json1.json, json2.json", "yml1.yml, yml2.yml", "yml1.yml, json2.json"})
    @DisplayName("Should correctly compare files of same format")
    void testGenerateSameFormat(String fileName1, String fileName2) throws Exception {
        Path file1 = getFixturePath(fileName1);
        Path file2 = getFixturePath(fileName2);

        String actual = Differ.generate(file1.toString(), file2.toString(), "stylish");
        assertEquals(expectedBasic, actual.trim());
    }

    @ParameterizedTest
    @CsvSource({"json1.json, json1.json", "yml1.yml, yml1.yml", "json1.json, yml1.yml"})
    @DisplayName("Should produce no diff for identical files")
    void testGenerateIdenticalFiles(String fileName1, String fileName2) throws Exception {
        Path file1 = getFixturePath(fileName1);
        Path file2 = getFixturePath(fileName2);

        String actual = Differ.generate(file1.toString(), file2.toString(), "stylish");
        assertEquals(expectedIdentical, actual.trim());
    }

    @ParameterizedTest
    @CsvSource({"json1.json, empty.json", "yml1.yml, empty.yml", "invalid.json, invalid.yml"})
    @DisplayName("Should throw exception for invalid or empty files")
    void testGenerateEmptyFile(String fileName1, String fileName2) {
        Path file1 = getFixturePath(fileName1);
        Path file2 = getFixturePath(fileName2);

        assertThrows(Exception.class, () -> Differ.generate(file1.toString(), file2.toString(), "stylish"));
    }

    @Test
    @DisplayName("Should throw when file does not exist")
    void testGenerateNonexistentFile() {
        Path nonexistent = getFixturePath("empty.json");
        assertThrows(Exception.class,
                () -> Differ.generate(nonexistent.toString(), nonexistent.toString(), "stylish"));
    }
}
