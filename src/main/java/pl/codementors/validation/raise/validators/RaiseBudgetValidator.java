package pl.codementors.validation.raise.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.codementors.validation.raise.model.RaiseRequest;
import pl.codementors.validation.raise.RaiseBudgetRepository;

@Component
@RequiredArgsConstructor
public class RaiseBudgetValidator {

    private final RaiseBudgetRepository raiseBudgetRepository;

    public boolean hasEnoughBudget(RaiseRequest raiseRequest) {
        var remainingBudget = raiseBudgetRepository.getRemainingBudget();
        var salaryDifference = raiseRequest.getProposedSalary().subtract(raiseRequest.getCurrentSalary());

        return remainingBudget.compareTo(salaryDifference) >= 0;
    }
}
