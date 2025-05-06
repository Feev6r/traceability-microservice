package ferv.dev.traceabilitymicroservice.category.application.services.impl;

import ferv.dev.traceabilitymicroservice.category.application.dto.response.EmployeeTraceResponse;
import ferv.dev.traceabilitymicroservice.category.application.mappers.EmployeeTraceabilityDtoMapper;
import ferv.dev.traceabilitymicroservice.category.application.services.EmployeeTraceabilityService;
import ferv.dev.traceabilitymicroservice.category.domain.ports.in.EmployeeTraceabilityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeTraceabilityServiceImpl implements EmployeeTraceabilityService {

    private final EmployeeTraceabilityPort employeeTraceabilityPort;
    private final EmployeeTraceabilityDtoMapper employeeTraceabilityDtoMapper;

    @Override
    public List<EmployeeTraceResponse> listEmployeeTrace(Integer page, Integer size, boolean orderAsc, List<Long> employeeIds) {
        return employeeTraceabilityDtoMapper.toResponseList(employeeTraceabilityPort.listEmployeTrace(page,size, orderAsc, employeeIds));
    }
}
