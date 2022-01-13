package pl.codementors.validation.raise.validators;

import pl.codementors.validation.raise.model.RaiseRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class MaxPercentageRaiseValidator implements ConstraintValidator<MaxPercentageRaise, RaiseRequest> {

    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private BigDecimal maxRaiseInPercents;

    @Override
    public void initialize(MaxPercentageRaise constraintAnnotation) {
        maxRaiseInPercents = BigDecimal.valueOf(constraintAnnotation.maxPercents());
    }

    @Override
    public boolean isValid(RaiseRequest value, ConstraintValidatorContext context) {
        var currentSalary = value.getCurrentSalary();
        var proposedSalary = value.getProposedSalary();
        if (Objects.isNull(currentSalary) || Objects.isNull(proposedSalary)) {
            return true;
        }


        var percents = proposedSalary.divide(currentSalary, 2, RoundingMode.HALF_UP).multiply(ONE_HUNDRED);
        var raiseInPercents = percents.subtract(ONE_HUNDRED);
        return raiseInPercents.compareTo(maxRaiseInPercents) <= 0;
    }
}
