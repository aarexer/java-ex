package flanagan.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class Compress {

    public static void gzipFile(final String filename, final String archiveName) throws IOException {
        final OutputStream out = new ZipOutputStream(new FileOutputStream(archiveName));
        final InputStream in = new FileInputStream(filename);
        final byte[] bytes = new byte[4096];

        int read_bytes;
        while ((read_bytes = in.read(bytes)) != -1) {
            out.write(bytes, 0, read_bytes);
        }

        out.close();
        in.close();
    }

    public static void zipDirectory(final String dirname, final String archiveName) throws IOException {
        final File dir = new File(dirname);

        if (!dir.exists()) {
            throw new IllegalArgumentException(String.format("Directory %s doesn't exist.", dirname));
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s isn't directory.", dirname));
        }

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(archiveName));
        final byte[] bytes = new byte[4096];
        int read_bytes;
        final String[] elements = dir.list();
        for (String name : elements) {
            final File elem = new File(dir, name);
            //todo
            if (elem.isDirectory()) continue;

            final InputStream input = new FileInputStream(elem);

            ZipEntry entry = new ZipEntry(elem.getPath());
            out.putNextEntry(entry);

            while ((read_bytes = input.read(bytes)) != -1) {
                out.write(bytes, 0, read_bytes);
            }
            input.close();
        }
        out.flush();
        out.close();
    }
}
