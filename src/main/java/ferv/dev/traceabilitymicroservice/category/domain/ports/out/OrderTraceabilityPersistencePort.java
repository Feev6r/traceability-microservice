package ferv.dev.traceabilitymicroservice.category.domain.ports.out;

import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;

public interface OrderTraceabilityPersistencePort {

    OrderTraceability saveOrderTrace(OrderTraceability orderTraceability);
    OrderTraceability getOrderTraceByOrderId(Long orderId);
}
