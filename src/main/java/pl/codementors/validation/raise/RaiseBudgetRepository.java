package pl.codementors.validation.raise;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RaiseBudgetRepository {

    private final BigDecimal remainingBudget = BigDecimal.valueOf(400);

    public BigDecimal getRemainingBudget() {
        return remainingBudget;
    }
}
