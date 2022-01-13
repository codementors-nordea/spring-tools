package pl.codementors.validation.raise.model;

import lombok.Value;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Value
public class AcceptedRaise {
    private static final String ID_FORMAT = "%s-%s";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MMM.yyyy");

    @Pattern(regexp = "\\d+\\.\\w+\\.\\d+-\\d+")
    String id;
    LocalDate date;
    BigDecimal acceptedSalary;

    public static AcceptedRaise forRequest(RaiseRequest raiseRequest) {
        var date = raiseRequest.getProposedRaiseDate();
        var proposedSalary = raiseRequest.getProposedSalary().intValue();
        var id = String.format(ID_FORMAT, date.format(DATE_FORMATTER), proposedSalary);

        return new AcceptedRaise(id, date, raiseRequest.getProposedSalary());
    }

}
