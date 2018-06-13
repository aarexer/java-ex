package examples.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * All above techniques will read the content of file and print it to console.
 */
public final class OldApproaches {
    /**
     * Old way to read file without nio.
     * Create BufferedReader and read file line by line.
     * Requires Java 1.7+ cause use try-with-resources and Objects.
     *
     * @param path path to file.
     * @return List of lines.
     * @throws IOException when an error occurs.
     */
    static List<String> readAll(String path) throws IOException {
        if (Objects.isNull(path)) {
            throw new IllegalArgumentException("Path can't be null");
        }

        try (final FileReader fr = new FileReader(path);
             final BufferedReader br = new BufferedReader(fr)) {

            String line;
            final List<String> content = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                content.add(line);
                System.out.println(line);
            }

            return content;
        }
    }
}
