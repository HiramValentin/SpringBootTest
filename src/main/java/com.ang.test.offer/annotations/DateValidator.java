package com.ang.test.offer.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateValidator implements ConstraintValidator<ValidDate, Date> {
    public void initialize(ValidDate constraint) {
    }

    public boolean isValid(Date value, ConstraintValidatorContext context) {
        return false;
    }
}
