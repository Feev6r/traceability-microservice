package ferv.dev.traceabilitymicroservice.category.application.dto.request;

import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;
import jakarta.validation.constraints.NotNull;

public record OrderTraceabilityRequest(
        @NotNull
        Long clientId,
        @NotNull
        Long orderId,
        @NotNull
        OrderStates state
) {
}
