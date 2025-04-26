package ferv.dev.traceabilitymicroservice.category.application.services.impl;

import ferv.dev.traceabilitymicroservice.category.application.dto.request.OrderTraceabilityRequest;
import ferv.dev.traceabilitymicroservice.category.application.dto.response.OrderTraceResponse;
import ferv.dev.traceabilitymicroservice.category.application.mappers.OrderTraceResponseMapper;
import ferv.dev.traceabilitymicroservice.category.application.mappers.OrderTraceabilityDtoMapper;
import ferv.dev.traceabilitymicroservice.category.application.services.OrderTraceabilityService;
import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;
import ferv.dev.traceabilitymicroservice.category.domain.ports.in.OrderTraceabilityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderTraceabilityServiceImpl implements OrderTraceabilityService {

    private final OrderTraceabilityPort orderTraceabilityPort;
    private final OrderTraceabilityDtoMapper orderTraceabilityDtoMapper;
    private final OrderTraceResponseMapper orderTraceResponseMapper;

    @Override
    public void createOrderTrace(OrderTraceabilityRequest request) {
        orderTraceabilityPort.createOrderTrace(orderTraceabilityDtoMapper.toModel(request));
    }

    @Override
    public void updateOrderTrace(Long orderId, OrderStates state) {
        orderTraceabilityPort.orderUpdateTrace(orderId, state);
    }

    @Override
    public void updateOrderTraceByAssigningEmployee(Long employeeId, Long orderId, OrderStates state) {
        orderTraceabilityPort.updateOrderTraceByAssigningEmployee(employeeId, orderId, state);
    }

    @Override
    public List<OrderTraceResponse> listOrderTrace(Long orderId) {
        return orderTraceResponseMapper.toOrderTraceResponseList(orderTraceabilityPort.listOrderTrace(orderId));
    }
}
