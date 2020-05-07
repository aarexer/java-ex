package examples.java8;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class StringExercise {
    public static void main(String[] args) {
        //  Подсчитайте количество строчных букв в строке (подсказка: воспользуйтесь методом chars класса String).
        long lowerCaseCount = "Hello World".chars().filter(Character::isLowerCase).count();
        long upperCaseCount = "Hello World".chars().filter(Character::isUpperCase).count();
        System.out.println(lowerCaseCount);
        System.out.println(upperCaseCount);


        //  Пусть дан список строк List<String>.
        //  Найдите в нем строку, содержащую максимальное число строчных букв.
        //  Чтобы код правильно работал, когда входной список пуст, можете возвращать объект типа Optional <String>.
        Optional<String> max = Stream.of("Hello", "Hi", "Grazi").max(Comparator.comparing(s -> s.chars().filter(Character::isLowerCase).count()));
        System.out.println(max);
    }
}
