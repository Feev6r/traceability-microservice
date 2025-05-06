package ferv.dev.traceabilitymicroservice.category.infrastructure.adapters.persistence;

import ferv.dev.traceabilitymicroservice.category.domain.models.EmployeeTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.EmployeeTraceabilityPersistencePort;
import ferv.dev.traceabilitymicroservice.category.infrastructure.exeptionshandler.exceptions.EmployeeTraceNotFound;
import ferv.dev.traceabilitymicroservice.category.infrastructure.mappers.EmployeeTraceabilityMapper;
import ferv.dev.traceabilitymicroservice.category.infrastructure.repositories.mongodb.EmployeeTraceabilityRepository;
import ferv.dev.traceabilitymicroservice.commons.configuration.utils.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeTraceabilityAdapter implements EmployeeTraceabilityPersistencePort {

    private final EmployeeTraceabilityRepository repository;
    private final EmployeeTraceabilityMapper employeeTraceabilityMapper;

    @Override
    public List<EmployeeTraceability> listEmployeeTrace(Integer page, Integer size, boolean orderAsc, List<Long> employeeIds) {
        Pageable pagination;
        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_EFFICIENCY).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_EFFICIENCY).descending());

        List<String> employeeIdsString = employeeIds.stream()
                .map(Object::toString)
                .toList();

        return employeeTraceabilityMapper.toModelList(repository.findByEmployeeIdIn(employeeIdsString, pagination).getContent());
    }

    @Override
    public EmployeeTraceability saveEmployeeTrace(EmployeeTraceability employeeTraceability) {
        return employeeTraceabilityMapper.toModel(repository.save(employeeTraceabilityMapper.toDocument(employeeTraceability)));
    }

    @Override
    public EmployeeTraceability getEmployeeTraceByEmployee(Long employeeId) {
        return employeeTraceabilityMapper.toModel(repository.findByEmployeeId(employeeId.toString())
                .orElseThrow(EmployeeTraceNotFound::new));
    }
}
