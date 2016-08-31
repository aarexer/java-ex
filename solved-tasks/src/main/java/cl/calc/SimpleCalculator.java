//package cl.calc;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//import java.util.Deque;
//import java.util.StringTokenizer;
//
///**
// * Консольное приложение, на вход принимает параметр-строку:
// * java Calculator "1 + 2 + 3 - (4 * 5 / 2)"
// * Результат работы приложения -- результат выражения: -4
// * Поддерживаемые операции: + - * /. Приоритет операций -- математический (скобки, потом умножение/деление, потом сложение/вычитание).
// * В случае неправильных входных данных приложение должно вывести понятное описание ошибки.
// */
//public class SimpleCalculator {
//    private static final Logger logger = LogManager.getLogger(SimpleCalculator.class);
//    private static Deque<Double> queue = new ArrayDeque<>();
//
//    public static void main(String[] args) {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter string: ");
//        try {
//            String lineToParse = br.readLine();
//
//        } catch (IOException e) {
//            logger.error("Error while reading input, error message: {}", e.getMessage());
//        }
//
//    }
//
//    private static double calculate(String line) {
//        final StringTokenizer st = new StringTokenizer(line);
//        String token;
//        Double result, number;
//
//        while (st.hasMoreTokens()) {
//            token = st.nextToken().trim();
//            if (isOperation(token.charAt(0))) {
//
//            }
//        }
//    }
//
//    private static boolean isOperation(char ch) {
//        switch (ch) {
//            case '+':
//            case '-':
//            case '*':
//            case '/':
//            case '^':
//                return true;
//        }
//
//        return false;
//    }
//
//    private static int operationPriority(char ch) {
////        switch (ch) {
////            case '/'
////        }
//        return 0;
//    }
//}
