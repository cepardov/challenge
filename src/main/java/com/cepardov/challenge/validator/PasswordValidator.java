package com.cepardov.challenge.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cepardov on 01-08-20
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private final String regex = "^(?=.{2,}[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$";

    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
