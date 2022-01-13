package pl.codementors.validation.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;
import pl.codementors.validation.raise.model.RaiseRequest;

import java.math.BigDecimal;

@Component
public class RaiseRequestMetrics {

    private BigDecimal lastRejectedValue;

    private final Gauge lastRejectedRaiseValue;
    private final Counter rejectedRequestsCounter;

    public RaiseRequestMetrics(MeterRegistry meterRegistry) {
        this.lastRejectedRaiseValue = Gauge.builder("raises.last.rejected", () -> lastRejectedValue).register(meterRegistry);
        this.rejectedRequestsCounter = Counter.builder("raises.total.count").register(meterRegistry);
    }

    public void rejectRaiseRequest(RaiseRequest raiseRequest) {
        rejectedRequestsCounter.increment();
        lastRejectedValue = raiseRequest.getProposedSalary();
    }
}
