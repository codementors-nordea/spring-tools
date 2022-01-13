package pl.codementors.validation.raise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.codementors.validation.metrics.RaiseRequestMetrics;
import pl.codementors.validation.raise.model.AcceptedRaise;
import pl.codementors.validation.raise.model.RaiseRequest;
import pl.codementors.validation.raise.validators.NotEnoughBudgetException;
import pl.codementors.validation.raise.validators.RaiseBudgetValidator;

@Component
@RequiredArgsConstructor
public class RaiseService {

    private final RaiseRequestMetrics raiseRequestMetrics;
    private final RaiseBudgetValidator raiseBudgetValidator;

    public AcceptedRaise analyzeRaiseRequest(RaiseRequest raiseRequest) {
        if (!raiseBudgetValidator.hasEnoughBudget(raiseRequest)) {
            raiseRequestMetrics.rejectRaiseRequest(raiseRequest);
            throw new NotEnoughBudgetException();
        }

        return AcceptedRaise.forRequest(raiseRequest);
    }
}
