package org.softuin.mobilele.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.softuin.mobilele.repository.UserRepository;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {


    private final UserRepository userRepository;


    public UniqueUserEmailValidator(UserRepository userRepository){

        this.userRepository = userRepository;
    }
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return userRepository.findByEmail(s).isEmpty();
    }
}
