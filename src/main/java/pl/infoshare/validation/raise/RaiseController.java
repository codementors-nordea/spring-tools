package pl.infoshare.validation.raise;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.validation.raise.model.AcceptedRaise;
import pl.infoshare.validation.raise.model.RaiseRequest;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class RaiseController {

    private final RaiseService raiseService;

    @PostMapping("/api/raises")
    @Operation(description = "Endpoint zajmujący się analizą poprawności prośby o podwyżkę. Podwyżka nie może przekroczyć 30% aktualnej pensji i musi się mieścić w pozostałym budżecie.")
    public AcceptedRaise analyzeRaiseRequest(@Valid @RequestBody RaiseRequest raiseRequest) {
        return raiseService.analyzeRaiseRequest(raiseRequest);
    }
}
