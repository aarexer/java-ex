package utils.hash;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

public final class HashUtils {
    private HashUtils() {
    }

    /**
     * Md5 hash of file with hex binary format using javax.
     *
     * @param path to file.
     * @return String of md5 hash with hex binary format.
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String fileMd5(String path) throws NoSuchAlgorithmException, IOException {
        final File file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException(String.format("File %s doesn't exist", path));
        }

        final MessageDigest md = MessageDigest.getInstance("MD5");
        final List<String> content = FileUtils.readLines(file, Charset.forName("UTF-8"));
        final String forHash = String.join("|", content);

        md.update(forHash.getBytes());
        byte[] digest = md.digest();

        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }

    /**
     * The same as below, but with Guava.
     * @param path
     * @return
     * @throws IOException
     */
    public static String fileMd5Guava(String path) throws IOException {
        final File file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException(String.format("File %s doesn't exist", path));
        }

        HashCode hash = com.google.common.io.Files.asByteSource(file).hash(Hashing.goodFastHash(256));

        return hash.toString()
                .toUpperCase();
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        System.out.println(fileMd5("pom.xml"));
    }
}
