package utils.io.streams;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.ZipInputStream;

/**
 * Get input stream of zip-resource and return common buffered reader
 * for all in archive.
 */
public class ZipSeqInputStream extends InputStream {
    private final ZipInputStream zip;

    public ZipSeqInputStream(InputStream input) {
        this.zip = new ZipInputStream(input);
    }

    @Override
    public int read() throws IOException {
        int c = zip.read();
        if (c != -1) {
            return c;
        }

        if (Objects.isNull(zip.getNextEntry())) {
            return -1;
        }

        return zip.read();
    }
}
