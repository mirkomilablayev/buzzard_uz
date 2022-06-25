package uz.buzzard.buzzard_uz.tools.config.anotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRole {
 String value();
}
