package samples.resources;

import java.io.InputStream;
import java.util.Scanner;

public class ResourceExample {
    public static void main(String[] args) {
        printAbsResource();
        printRelResource();

        System.out.println();

        npeAbsExample();
        npeRelExample();
    }

    private static void npeAbsExample() {
        InputStream resourceAbsoluteInputStream = ResourceExample.class.getResourceAsStream("/testRel.txt");
        if (resourceAbsoluteInputStream == null)
            System.out.println("Can't find resource by absolute path");
    }

    private static void npeRelExample() {
        InputStream resourceRelInputStream = ResourceExample.class.getResourceAsStream("testAbs.txt");
        if (resourceRelInputStream == null)
            System.out.println("Can't find resource by relative path");
    }

    private static void printAbsResource() {
        InputStream resourceAbsoluteInputStream = ResourceExample.class.getResourceAsStream("/testAbs.txt");
        System.out.println("Absolute:\n");

        Scanner scanner = new Scanner(resourceAbsoluteInputStream);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    private static void printRelResource() {
        InputStream resourceRelativeInputStream = ResourceExample.class.getResourceAsStream("testRel.txt");
        System.out.println("\nRelative:\n");

        Scanner scannerR = new Scanner(resourceRelativeInputStream);
        while (scannerR.hasNext()) {
            System.out.println(scannerR.next());
        }
    }
}
