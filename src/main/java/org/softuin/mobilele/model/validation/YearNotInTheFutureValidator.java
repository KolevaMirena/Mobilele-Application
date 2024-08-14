package org.softuin.mobilele.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearNotInTheFutureValidator implements ConstraintValidator<YearNotInTheFuture, Number> {

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {

        if(number == null){
            return true;
        }else{

            int currentYear = Year.now().getValue();
            return currentYear >= number.intValue();
        }

    }
}
