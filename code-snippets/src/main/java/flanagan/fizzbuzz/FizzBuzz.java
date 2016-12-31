package flanagan.fizzbuzz;

public class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            fizzbuzz(i);
        }
    }

    private static void fizzbuzz(int num) {
        boolean outputFlag = false;

        if (num % 5 == 0) {
            System.out.print("Fizz");
            outputFlag = true;
        }

        if (num % 7 == 0) {
            System.out.print("Buzz");
            outputFlag = true;
        }

        if (!outputFlag) {
            System.out.print(num);
        }

        //newline
        System.out.println();
    }
}
