package pl.infoshare.validation.actuator.health;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Validated
@ConstructorBinding
@ConfigurationProperties("health.budget")
public class BudgetHealthProperties {

    @NotNull
    @DecimalMax("1000")
    BigDecimal threshold;
}
