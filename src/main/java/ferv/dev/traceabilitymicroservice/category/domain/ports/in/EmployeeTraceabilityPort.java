package ferv.dev.traceabilitymicroservice.category.domain.ports.in;

import ferv.dev.traceabilitymicroservice.category.domain.models.EmployeeTraceability;

import java.util.List;

public interface EmployeeTraceabilityPort {
    List<EmployeeTraceability> listEmployeTrace(Integer page, Integer size, boolean orderAsc, List<Long> employeeIds);
    void calculateAverageEfficiency(Long assignedEmployeeId);
    EmployeeTraceability createEmployeeTrace(Long employeeId);
}
