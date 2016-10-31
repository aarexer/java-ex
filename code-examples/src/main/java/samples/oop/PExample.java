package samples.oop;

/**
 * Example for linking
 */
public class PExample {
    public static void main(String[] args) {
        User user = new Admin();
        use(user); // Admin invoke test
    }

    public static void use(User user) {
        user.test();
    }
}

/**
 * Parent class
 */
class User {
    public void test() {
        System.out.println("User invoke test");
    }
}

/**
 * User class
 */
class Admin extends User {
    @Override
    public void test() {
        System.out.println("Admin invoke test");
    }
}
