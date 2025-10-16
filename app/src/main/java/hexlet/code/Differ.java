package hexlet.code;

import java.io.IOException;

public final class Differ {

    private Differ() {
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        var dataOfFile1 = Parser.parseFile(filePath1);
        var dataOfFile2 = Parser.parseFile(filePath2);
        return Formatter.format(dataOfFile1, dataOfFile2, format);
    }
}
