package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedStylishIdentical;
    private static String expectedPlainIdentical;

    @BeforeAll
    static void setUp() throws Exception {
        expectedStylish = readFixture("expected_stylish.txt");
        expectedPlain = readFixture("expected_plain.txt");
        expectedStylishIdentical = readFixture("expected_stylish_Identical.txt");
        expectedPlainIdentical = readFixture("expected_plain_Identical.txt");
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @ParameterizedTest
    @CsvSource({"json1.json, json2.json", "yml1.yml, yml2.yml", "yml1.yml, json2.json"
    })
    @DisplayName("Should correctly compare files (stylish)")
    void testGenerateStylish(String fileName1, String fileName2) throws Exception {
        Path file1 = getFixturePath(fileName1);
        Path file2 = getFixturePath(fileName2);

        String actual = Differ.generate(file1.toString(), file2.toString(), "stylish");
        assertEquals(expectedStylish, actual.trim());
    }

    @ParameterizedTest
    @CsvSource({"json1.json, json1.json", "yml1.yml, yml1.yml", "json1.json, yml1.yml"
    })
    @DisplayName("Should produce no diff for identical files (stylish)")
    void testGenerateStylishIdentical(String fileName1, String fileName2) throws Exception {
        Path file1 = getFixturePath(fileName1);
        Path file2 = getFixturePath(fileName2);

        String actual = Differ.generate(file1.toString(), file2.toString(), "stylish");
        assertEquals(expectedStylishIdentical, actual.trim());
    }

    @ParameterizedTest
    @CsvSource({"json1.json, json2.json", "yml1.yml, yml2.yml", "yml1.yml, json2.json"
    })
    @DisplayName("Should correctly compare files (plain)")
    void testGeneratePlain(String fileName1, String fileName2) throws Exception {
        Path file1 = getFixturePath(fileName1);
        Path file2 = getFixturePath(fileName2);

        String actual = Differ.generate(file1.toString(), file2.toString(), "plain");
        assertEquals(expectedPlain, actual.trim());
    }

    @ParameterizedTest
    @CsvSource({"json1.json, json1.json", "yml1.yml, yml1.yml", "json1.json, yml1.yml"
    })
    @DisplayName("Should produce no diff for identical files (plain)")
    void testGeneratePlainIdentical(String fileName1, String fileName2) throws Exception {
        Path file1 = getFixturePath(fileName1);
        Path file2 = getFixturePath(fileName2);

        String actual = Differ.generate(file1.toString(), file2.toString(), "plain");
        assertEquals(expectedPlainIdentical, actual.trim());
    }

    @ParameterizedTest
    @CsvSource({"json1.json, empty.json", "yml1.yml, empty.yml", "invalid.json, invalid.yml"})
    @DisplayName("Should throw exception for invalid or empty files")
    void testGenerateEmptyFile(String fileName1, String fileName2) {
        Path file1 = getFixturePath(fileName1);
        Path file2 = getFixturePath(fileName2);

        assertThrows(Exception.class, () -> Differ.generate(file1.toString(), file2.toString(), "stylish"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "stylish", "plain"})
    @DisplayName("Should throw when file does not exist")
    void testGenerateNonexistentFile(String format) {
        Path nonexistent = getFixturePath("nonexistent.json");
        assertThrows(Exception.class,
                () -> Differ.generate(nonexistent.toString(), nonexistent.toString(), format));
    }
}
