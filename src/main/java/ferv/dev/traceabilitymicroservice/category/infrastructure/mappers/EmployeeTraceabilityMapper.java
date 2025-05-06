package ferv.dev.traceabilitymicroservice.category.infrastructure.mappers;

import ferv.dev.traceabilitymicroservice.category.domain.models.EmployeeTraceability;
import ferv.dev.traceabilitymicroservice.category.infrastructure.documents.mongodb.EmployeeTraceabilityDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeTraceabilityMapper {

    EmployeeTraceability toModel(EmployeeTraceabilityDocument employeeTraceabilityDocument);
    List<EmployeeTraceability> toModelList(List<EmployeeTraceabilityDocument> employeeTraceabilityDocumentList);
    EmployeeTraceabilityDocument toDocument(EmployeeTraceability employeeTraceability);

}
