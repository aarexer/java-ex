package flanagan.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class GetURLInfo {
    public static void printURLInfo(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        connection.connect();

        System.out.println(connection.getContentType());
        System.out.println(connection.getContentEncoding());
        System.out.println(connection.getContentLength());

        if (connection instanceof HttpURLConnection) {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            System.out.println(httpConnection.getRequestMethod());
            System.out.println(httpConnection.getResponseCode());
            System.out.println(httpConnection.getResponseMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        printURLInfo(new URL("http://www.yandex.ru"));
    }
}
