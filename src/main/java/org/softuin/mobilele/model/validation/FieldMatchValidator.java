package org.softuin.mobilele.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.Objects;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    String first;
    String second;
    String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.first=constraintAnnotation.first();
        this.second=constraintAnnotation.second();
        this.message=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(o);
        Object propertyValueFirst = beanWrapper.getPropertyValue(this.first);
        Object propertyValueSecond = beanWrapper.getPropertyValue(this.second);


        boolean isValid = Objects.equals(propertyValueFirst, propertyValueSecond);

        if(!isValid){
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(second)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();


        }
        return isValid;
    }
}
