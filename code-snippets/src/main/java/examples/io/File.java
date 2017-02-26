package examples.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * All above techniques will read the content of file and print it to console.
 */
public class File {
    /**
     * Old way to read file without nio.
     *
     * @param name filename.
     * @throws IOException if error.
     */
    void readOldWay(String name) throws IOException {
        try (final BufferedReader br = new BufferedReader(new FileReader(name))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * For small files, reading by byte buffer.
     *
     * @param name filename.
     * @throws IOException if error.
     */
    void readByByteBufferNio(String name) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(name, "r");
             FileChannel inChannel = raf.getChannel()) {

            final long size = inChannel.size();
            final ByteBuffer buffer = ByteBuffer.allocate((int) size);
            inChannel.read(buffer);
            buffer.flip();

            for (int i = 0; i < size; i++) {
                System.out.print((char) buffer.get());
            }
        }
    }

    /**
     * For large files, reading by chunks with fixed buffer sized.
     *
     * @param name filename.
     * @throws IOException if error.
     */
    void readByByteBufferNioLarge(String name) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(name, "r");
             FileChannel inChannel = raf.getChannel()) {

            final long size = inChannel.size();
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear(); // do something with the data and clear/compact it.
            }
        }
    }

    /**
     * Fast way, mapping byte buffer.
     *
     * @param name filename.
     * @throws IOException if error.
     */
    void readByMappedByteBuffer(String name) throws IOException {
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
