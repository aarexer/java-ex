package core.nio;

public class Example {
    public static void main(String[] args) {
        DirectoryWathcer dw = new DirectoryWathcer("utils");
        dw.addIgnoreName("utils.iml");
        dw.addIgnoreName("utils-ex.iml");

        dw.startMonitoring();
    }
}
