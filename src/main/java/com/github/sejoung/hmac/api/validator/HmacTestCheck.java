package com.github.sejoung.hmac.api.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = HmacTestValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface HmacTestCheck {
    String message() default "HmacTestCheck error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
