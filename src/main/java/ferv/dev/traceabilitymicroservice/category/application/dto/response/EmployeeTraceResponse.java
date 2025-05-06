package ferv.dev.traceabilitymicroservice.category.application.dto.response;

public record EmployeeTraceResponse(
        Long employeeId,
        Long averageEfficiencyInMilliseconds
) {
}
