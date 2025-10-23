package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Differ {

    private Differ() {
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        var path1 = Path.of(filePath1);
        var path2 = Path.of(filePath2);
        var content1 = Files.readString(path1);
        var content2 = Files.readString(path2);
        var dataFormat1 = ExtensionFinder.findExtension(path1.getFileName().toString());
        var dataFormat2 = ExtensionFinder.findExtension(path2.getFileName().toString());
        var data1 = Parser.parse(content1, dataFormat1);
        var data2 = Parser.parse(content2, dataFormat2);
        var diff = DiffFinder.findDiff(data1, data2);
        return Formatter.formatData(diff, format);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
}
