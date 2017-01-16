package flanagan.io.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {

    public static void delete(String name) throws IOException {
        final Path directory = Paths.get(name);

        if (Files.notExists(directory)) {
            throw new IllegalArgumentException("Can't delete non existing file " + directory.getFileName());
        }

        if (!Files.isWritable(directory)) {
            throw new IllegalArgumentException("Can't delete, cause access denied");
        }

        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
