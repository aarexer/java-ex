package examples;

import java.net.URISyntaxException;

public class ResourcesTest {
    public static void main(String[] args) throws URISyntaxException {
        ResourcesTest resourcesTest = new ResourcesTest();
        resourcesTest.printURIOfRelResource();
        resourcesTest.printURIOfAbsResource();

        //static
        printURIOfResourceFromStatic();
    }

    private void printURIOfAbsResource() throws URISyntaxException {
        System.out.println(getClass().getResource("/absPath.txt").toURI());
    }

    private void printURIOfRelResource() throws URISyntaxException {
        System.out.println(getClass().getResource("relPath.txt").toURI());
    }

    private static void printURIOfResourceFromStatic() throws URISyntaxException {
        System.out.println(ResourcesTest.class.getClassLoader().getResource("absPath.txt"));
    }
}