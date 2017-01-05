package examples;

import java.net.URISyntaxException;

public class ResourcesTest {
    public static void main(String[] args) {
        new ResourcesTest().testRelPath();
    }

    void testAbsPath() {
        try {
            System.out.println(this.getClass().getResource("/absPath.txt").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    void testRelPath() {
        try {
            System.out.println(this.getClass().getResource("relPath.txt").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
