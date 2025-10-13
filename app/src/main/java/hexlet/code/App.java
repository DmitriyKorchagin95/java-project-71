package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: stylish]")
    private String format;

    @Parameters(index = "0", paramLabel = "filepath1", description = "Path to first file.")
    private String filePath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "Path to second file.")
    private String filePath2;

    @Override
    public Integer call() throws Exception {
        var diff = Differ.generate(filePath1, filePath2);
        System.out.println(diff);
        return 0;
    }

    public static void main(String... args) {

        var colorScheme = new CommandLine.Help.ColorScheme.Builder(CommandLine.Help.Ansi.ON)
                .options(CommandLine.Help.Ansi.Style.fg_red)
                .parameters()
                .applySystemProperties()
                .build();

        int exitCode = new CommandLine(new App())
                .setColorScheme(colorScheme)
                .execute(args);

        System.exit(exitCode);
    }
}
