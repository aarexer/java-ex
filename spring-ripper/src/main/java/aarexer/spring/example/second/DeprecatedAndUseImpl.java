package aarexer.spring.example.second;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DeprecatedAndUseImpl {
    Class newImpl();
}
