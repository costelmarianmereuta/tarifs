package com.marian.tennis.api.tarifs.utils.validators;

import com.marian.tennis.api.tarifs.utils.Constants;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidAfterDateValidator.class})
@Documented
public @interface ValidAfterDate {

    String message() default Constants.INVALID_DATE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}