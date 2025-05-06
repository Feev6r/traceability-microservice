package ferv.dev.traceabilitymicroservice.category.application.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EmployeeIdsRequest(
        @NotNull
        List<Long> employeeIds
) {
}
