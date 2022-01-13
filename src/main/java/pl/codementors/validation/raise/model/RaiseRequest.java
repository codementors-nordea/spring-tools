package pl.codementors.validation.raise.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import pl.codementors.validation.raise.validators.MaxPercentageRaise;
import pl.codementors.validation.raise.validators.MaxRaiseDate;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Value
@MaxPercentageRaise(maxPercents = 30)
public class RaiseRequest {
    @NotNull
    BigDecimal currentSalary;

    @Future
    @MaxRaiseDate
    @Schema(description = "Podwyżki zaplanowane na późniejszy czas niż najbliższe 3 miesiące nie będą rozpatrywane")
    LocalDate proposedRaiseDate;

    BigDecimal proposedSalary;

    @NotNull
    @Size(max = 2)
    @Valid
    @Schema(description = "Ilość poprzednich podwyżek. Ich łączna ilość nie może przekraczać dwóch." )
    List<AcceptedRaise> previousRaises;
}
