package com.ang.test.offer.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface ValidDate {

    String message() default "some message here";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}