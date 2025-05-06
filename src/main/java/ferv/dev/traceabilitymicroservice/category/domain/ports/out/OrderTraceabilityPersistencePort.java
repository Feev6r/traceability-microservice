package ferv.dev.traceabilitymicroservice.category.domain.ports.out;

import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;

import java.util.List;

public interface OrderTraceabilityPersistencePort {

    OrderTraceability saveOrderTrace(OrderTraceability orderTraceability);
    OrderTraceability getOrderTraceByOrderId(Long orderId);
    List<OrderTraceability> listOrderTraceabilityByAssignedEmployee(Long employeeId);
}
