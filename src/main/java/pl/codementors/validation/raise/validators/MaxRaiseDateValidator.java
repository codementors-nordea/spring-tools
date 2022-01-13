package pl.codementors.validation.raise.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MaxRaiseDateValidator implements ConstraintValidator<MaxRaiseDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value.isBefore(LocalDate.now().plusMonths(3));
    }
}
