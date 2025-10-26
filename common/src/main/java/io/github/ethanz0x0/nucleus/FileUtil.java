package io.github.ethanz0x0.nucleus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static io.github.ethanz0x0.nucleus.Checks.checkArgument;
import static io.github.ethanz0x0.nucleus.Checks.checkNotNull;

/**
 * A utility class for processing files.
 */
public class FileUtil {

    /**
     * Reads file as string, '\n' as line breaker.
     *
     * @param file
     *        File to be read as string
     * @return
     *         String content of the file
     */
    public static String readContentAsString(File file) throws IOException {
        checkNotNull(file, "file cannot be null");

        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    /**
     * Formats file length as string.
     *
     * @param fileLength
     *        File length to be formatted
     * @return
     *         The formatted file length (e.g. 152MB)
     */
    public static String formatFileLength(long fileLength) {
        checkArgument(fileLength >= 0, "file length must be positive");

        String[] units = new String[]{"B", "KB", "MB", "GB", "TB", "PB", "EB"};
        int unitIndex = 0;
        double len = fileLength;

        while (len >= 1024 && unitIndex < units.length) {
            len /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", len, units[unitIndex]);
    }
}
