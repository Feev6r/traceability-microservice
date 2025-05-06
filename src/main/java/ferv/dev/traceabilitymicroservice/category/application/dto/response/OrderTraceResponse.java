package ferv.dev.traceabilitymicroservice.category.application.dto.response;

import java.util.List;

public record OrderTraceResponse(
        Long assignedEmployeeId,
        List<OrderTrackResponse> orderTrack,
        Long orderDurationInMilliseconds
) {
}
