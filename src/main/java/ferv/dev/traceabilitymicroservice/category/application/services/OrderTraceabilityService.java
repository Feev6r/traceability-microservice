package ferv.dev.traceabilitymicroservice.category.application.services;

import ferv.dev.traceabilitymicroservice.category.application.dto.request.OrderTraceabilityRequest;
import ferv.dev.traceabilitymicroservice.category.application.dto.response.OrderTraceResponse;
import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;

import java.util.List;

public interface OrderTraceabilityService {

    void createOrderTrace(OrderTraceabilityRequest request);
    void updateOrderTrace(Long orderId, OrderStates state);
    void updateOrderTraceByAssigningEmployee(Long employeeId, Long orderId, OrderStates state);
    List<OrderTraceResponse> listOrderTrace(Long orderId);

}
