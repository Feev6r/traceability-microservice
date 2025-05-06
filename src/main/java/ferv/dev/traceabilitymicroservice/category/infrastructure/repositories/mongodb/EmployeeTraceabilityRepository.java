package ferv.dev.traceabilitymicroservice.category.infrastructure.repositories.mongodb;

import ferv.dev.traceabilitymicroservice.category.infrastructure.documents.mongodb.EmployeeTraceabilityDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeTraceabilityRepository extends MongoRepository<EmployeeTraceabilityDocument, String> {

    Page<EmployeeTraceabilityDocument> findByEmployeeIdIn(List<String> employeeId, Pageable pageable);
    Optional<EmployeeTraceabilityDocument> findByEmployeeId(String employeeId);

}
