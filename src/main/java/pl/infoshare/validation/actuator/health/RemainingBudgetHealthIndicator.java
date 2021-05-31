package pl.infoshare.validation.actuator.health;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import pl.infoshare.validation.raise.RaiseBudgetRepository;

@Component
@RequiredArgsConstructor
public class RemainingBudgetHealthIndicator implements HealthIndicator {

    private static final String NO_BUDGET_STATUS = "no_budget";

    private final RaiseBudgetRepository raiseBudgetRepository;
    private final BudgetHealthProperties budgetHealthProperties;

    @Override
    public Health health() {
        return getStatus()
                .withDetail("min", budgetHealthProperties.getThreshold())
                .withDetail("current", raiseBudgetRepository.getRemainingBudget())
                .build();
    }

    private Health.Builder getStatus() {
        if (raiseBudgetRepository.getRemainingBudget().compareTo(budgetHealthProperties.getThreshold()) < 0) {
            return Health.status(NO_BUDGET_STATUS);
        }

        return Health.up();
    }
}
