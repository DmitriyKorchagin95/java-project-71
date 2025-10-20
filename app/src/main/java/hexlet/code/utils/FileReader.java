package hexlet.code.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileReader {

    private FileReader() {
    }

    public static String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath), Charset.defaultCharset());
    }
}
