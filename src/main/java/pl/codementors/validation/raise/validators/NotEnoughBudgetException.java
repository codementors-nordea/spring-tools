package pl.codementors.validation.raise.validators;

import pl.codementors.validation.exceptions.ApplicationException;

public class NotEnoughBudgetException extends ApplicationException {

    private static final String ERROR_CODE = "no-budget";
    private static final String ERROR_MESSAGE = "There is not enough budget to cover this raise request.";

    public NotEnoughBudgetException() {
        super(ERROR_MESSAGE, ERROR_CODE);
    }
}
