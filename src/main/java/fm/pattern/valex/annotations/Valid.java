package fm.pattern.valex.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Valid {

    /**
     * Specify one or more validation groups to apply to the validation step kicked off by this annotation.
     */
    Class<?>[] value() default {};

    boolean throwException() default false;
    
}
