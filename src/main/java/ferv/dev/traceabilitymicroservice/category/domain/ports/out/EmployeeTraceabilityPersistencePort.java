package ferv.dev.traceabilitymicroservice.category.domain.ports.out;

import ferv.dev.traceabilitymicroservice.category.domain.models.EmployeeTraceability;

import java.util.List;

public interface EmployeeTraceabilityPersistencePort {

    List<EmployeeTraceability> listEmployeeTrace(Integer page, Integer size, boolean orderAsc, List<Long> employeeIds);
    EmployeeTraceability saveEmployeeTrace(EmployeeTraceability employeeTraceability);
    EmployeeTraceability getEmployeeTraceByEmployee(Long employeeId);
}
