package ferv.dev.traceabilitymicroservice.category.application.mappers;

import ferv.dev.traceabilitymicroservice.category.application.dto.response.EmployeeTraceResponse;
import ferv.dev.traceabilitymicroservice.category.domain.models.EmployeeTraceability;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeTraceabilityDtoMapper {

    EmployeeTraceResponse toResponse(EmployeeTraceability employeeTraceability);
    List<EmployeeTraceResponse> toResponseList(List<EmployeeTraceability> employeeTraceabilityList);

}
