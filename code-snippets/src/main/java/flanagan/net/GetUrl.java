package flanagan.net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class GetUrl {
    public static void getUrl(final String address) throws IOException {
        final URL url = new URL(address);
        final InputStream is = url.openStream();
        final OutputStream os = new FileOutputStream("test.txt");

        final byte[] bytes = new byte[4096];
        int nread = 0;
        while ((nread = is.read(bytes)) != -1) {
            os.write(bytes, 0, nread);
        }

        is.close();
        os.close();
    }
}
