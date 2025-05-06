package ferv.dev.traceabilitymicroservice.category.infrastructure.repositories.mongodb;

import ferv.dev.traceabilitymicroservice.category.infrastructure.documents.mongodb.OrderTraceabilityDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderTraceabilityRepository extends MongoRepository<OrderTraceabilityDocument, String> {
    Optional<OrderTraceabilityDocument> findByOrderId(String orderId);
    List<OrderTraceabilityDocument> findAllByAssignedEmployeeId(String employeeId);
}
