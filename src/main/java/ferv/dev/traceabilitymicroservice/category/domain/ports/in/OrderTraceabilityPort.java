package ferv.dev.traceabilitymicroservice.category.domain.ports.in;

import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;

public interface OrderTraceabilityPort {

    void createOrderTrace(OrderTraceability orderTraceability);
    void orderUpdateTrace(Long orderId, OrderStates state);
    void updateOrderTraceByAssigningEmployee(Long employeeId, Long orderId, OrderStates state);
    OrderTraceability listOrderTrace(Long orderId);
}
