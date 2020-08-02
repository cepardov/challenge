package com.cepardov.challenge.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @author cepardov on 01-08-20
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {
}
