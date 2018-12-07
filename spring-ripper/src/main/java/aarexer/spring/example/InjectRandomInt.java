package aarexer.spring.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {
    int min();
    int max();
}
