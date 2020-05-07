package examples.interfaces;

public interface Math {
    static int abs(int a) {
        return (a < 0) ? -a : a;
    }
}
