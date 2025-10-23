package hexlet.code;

public final class ExtensionFinder {

    private ExtensionFinder() {
    }

    public static String findExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        String extension = "";

        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1);
        }

        return extension;
    }
}
