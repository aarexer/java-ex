package flanagan.io;

import java.io.*;
import java.nio.file.FileSystems;

/**
 * Delete file or directory.
 */
public class FileUtils {

    public static boolean delete(String filename) {
        final File file = new File(filename);

        if (!file.exists()) {
            throw new IllegalArgumentException("Can't delete non existing file " + filename);
        }

        if (file.isDirectory()) {
            String[] filesInDir = file.list();
            if (filesInDir == null) {
                throw new IllegalArgumentException("Invalid argument.");
            }

            if (filesInDir.length > 0) {
                for (String name : filesInDir) {
                    delete(filename + FileSystems.getDefault().getSeparator() + name);
                }
            }
        }

        return file.delete();
    }

    public static void copy(String sourceFile, String destinationFile) throws IOException {
        File source = new File(sourceFile);
        File dest = new File(destinationFile);

        if (!source.exists()) {
            throw new IllegalArgumentException("Source file doesn't exist.");
        }

        if (source.isDirectory()) {
            throw new IllegalArgumentException("Source file is directory.");
        }

        if (!source.canRead()) {
            throw new IllegalArgumentException("Source file hasn't read access.");
        }

        if (dest.exists()) {
            if (dest.isDirectory()) {
                throw new IllegalArgumentException("Destination file is directory.");
            }

            if (!dest.canWrite()) {
                throw new IllegalArgumentException("Destination file hasn't write access.");
            }

            System.out.println(String.format("WARN: Rewrite file %s", destinationFile));

        } else {
            String parent = dest.getParent();
            if (parent == null) {
                parent = System.getProperty("user.dir");
            }

            File pdir = new File(parent);

            if (!pdir.exists()) {
                throw new IllegalArgumentException(String.format("Parent directory %s for %s doesn't exist.", parent, dest));
            }

            if (!pdir.isDirectory()) {
                throw new IllegalArgumentException(String.format("Parent directory %s for %s is not directory.", parent, dest));
            }

            if (!pdir.canWrite()) {
                throw new IllegalArgumentException(String.format("Parent directory %s for %s hasn't write access.", parent, dest));
            }
        }

        try (FileInputStream from = new FileInputStream(dest);
             FileOutputStream to = new FileOutputStream(source)) {
            final byte[] bytes = new byte[4096];
            int nbytes = 0;
            while ((nbytes = from.read(bytes)) != -1) {
                to.write(bytes, 0, nbytes);
            }
        }
    }
}
