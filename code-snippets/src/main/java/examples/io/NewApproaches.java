package examples.io;

import java.io.BufferedReader;
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

public final class NewApproaches {
    /**
     * Improved old way to read file.
     * Create BufferedReader and read file line by line.
     * Requires Java 1.8+ cause use Files.
     *
     * @param path path to file.
     * @return List of lines.
     * @throws IOException when an error occurs.
     */
    static List<String> readAll(String path) throws IOException {
        if (Objects.isNull(path)) {
            throw new IllegalArgumentException("Path can't be null");
        }

        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {

            String line;
            final List<String> content = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                content.add(line);
                System.out.println(line);
            }

            return content;
        }
    }

    /**
     * Modern way to read all content to List of Strings.
     * Requires Java 1.8+ cause use Files.
     *
     * @param path to file.
     * @return List of lines.
     * @throws IOException when an error occurs.
     */
    static List<String> hipsterReadAll(String path) throws IOException {
        if (Objects.isNull(path)) {
            throw new IllegalArgumentException("Path can't be null");
        }

        return Files.readAllLines(Paths.get(path));
    }

    /**
     * New way to read content of file to String.
     * Requires Java 1.8+ cause use Files.
     *
     * @param path path to file.
     * @return String representation of the file.
     * @throws IOException when an error occurs.
     */
    static String readToString(String path) throws IOException {
        if (Objects.isNull(path)) {
            throw new IllegalArgumentException("Path can't be null");
        }

        return new String(Files.readAllBytes(Paths.get(path)));
    }

    /**
     * Read file content by byte buffer.
     * Requires Java 1.8+ cause use ByteBuffer, FileChannel and RandomAccessFile.
     *
     * @param name filename.
     * @throws IOException if error.
     */
    static String readToStringWithByteBuffer(String name) throws IOException {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Path can't be null");
        }

        try (RandomAccessFile raf = new RandomAccessFile(name, "r");
             FileChannel inChannel = raf.getChannel()) {

            final long size = inChannel.size();
            final ByteBuffer buffer = ByteBuffer.allocate((int) size);
            inChannel.read(buffer);
            buffer.flip();

            return String.valueOf(buffer.asCharBuffer().array());
        }
    }

    /**
     * For large files, reading by chunks with fixed buffer sized.
     * Requires Java 1.8+ cause use ByteBuffer, FileChannel and RandomAccessFile.
     *
     * @param name filename.
     * @throws IOException if error.
     */
    static void readByChunks(String name) throws IOException {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Path can't be null");
        }

        try (RandomAccessFile raf = new RandomAccessFile(name, "r");
             FileChannel inChannel = raf.getChannel()) {
            // size of chunk is 1024 bytes
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                // do something with the data and clear/compact it.

                for (int i = 0; i < buffer.limit(); i++) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
            }
        }
    }

    /**
     * Fast way, mapping byte buffer.
     *
     * @param name filename.
     * @throws IOException if error.
     */
    static void readByMappedByteBuffer(String name) throws IOException {
        try (RandomAccessFile aFile = new RandomAccessFile(name, "r");
             FileChannel inChannel = aFile.getChannel()) {

            final MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            buffer.load();
            for (int i = 0; i < buffer.limit(); i++) {
                System.out.print((char) buffer.get());
            }
            buffer.clear(); // do something with the data and clear/compact it.
        }
    }
}
