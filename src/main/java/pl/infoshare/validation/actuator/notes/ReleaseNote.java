package pl.infoshare.validation.actuator.notes;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ReleaseNote {
    String version;
    String link;
}
