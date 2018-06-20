package examples.exceptions;

public class TWRExample {
    public static void main(String[] args) {
        try (ExceptionOnClose close = new ExceptionOnClose()) {
            System.out.println("Wake up guy's!");
        } catch (Exception e) {
            System.out.println("While closing raised exception");
        }

        System.out.println("--------------------------------------------");
        try (RuntimeExceptionOnClose close = new RuntimeExceptionOnClose()) {
            System.out.println("Wake up guy's!");
        } catch (Exception e) {
            System.out.println("While closing raised runtime exception");
        }

        System.out.println("--------------------------------------------");
        try (WithoutExceptions close = new WithoutExceptions()) {
            System.out.println("Wake up guy's!");
        }
    }
}

class ExceptionOnClose implements AutoCloseable {

    @Override
    public void close() throws Exception {
        throw new Exception("Hello there!");
    }
}


class RuntimeExceptionOnClose implements AutoCloseable {

    @Override
    public void close() throws Exception {
        throw new RuntimeException("Hello there!");
    }
}

class WithoutExceptions implements AutoCloseable {

    @Override
    public void close() {
        System.out.println("Hello there!");
    }
}