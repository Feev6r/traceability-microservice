package ferv.dev.traceabilitymicroservice.category.application.services;

import ferv.dev.traceabilitymicroservice.category.application.dto.response.EmployeeTraceResponse;

import java.util.List;

public interface EmployeeTraceabilityService {
    List<EmployeeTraceResponse> listEmployeeTrace(Integer page, Integer size, boolean orderAsc, List<Long> employeeIds);

}
