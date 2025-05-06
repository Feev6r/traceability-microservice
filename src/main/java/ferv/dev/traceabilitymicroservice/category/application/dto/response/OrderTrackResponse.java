package ferv.dev.traceabilitymicroservice.category.application.dto.response;

import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;

import java.time.Instant;

public record OrderTrackResponse(
        Instant timeStamp,
        OrderStates state
) {

}
