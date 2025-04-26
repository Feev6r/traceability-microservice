package ferv.dev.traceabilitymicroservice.category.domain.usecases;

import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTrackingModel;
import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;
import ferv.dev.traceabilitymicroservice.category.domain.ports.in.OrderTraceabilityPort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.OrderTraceabilityPersistencePort;

import java.time.Instant;

public class OrderTraceabilityUseCase implements OrderTraceabilityPort {

    private final OrderTraceabilityPersistencePort persistencePort;

    public OrderTraceabilityUseCase(OrderTraceabilityPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public void createOrderTrace(OrderTraceability orderTraceability) {
        //Set the timestamp on the last record which is the one just created
        orderTraceability.getOrderTracking().getLast().setTimeStamp(Instant.now());

        persistencePort.saveOrderTrace(orderTraceability);
    }

    @Override
    public void orderUpdateTrace(Long orderId, OrderStates state) {
        OrderTraceability orderTraceability = persistencePort.getOrderTraceByOrderId(orderId);
        orderTraceability.getOrderTracking().add(new OrderTrackingModel(state, Instant.now()));

        persistencePort.saveOrderTrace(orderTraceability);

        //        if(state = OrderStates.READY){
        //TODO CALL EMPLOYEETRACE USECASE
//        }
    }

    @Override
    public void updateOrderTraceByAssigningEmployee(Long employeeId, Long orderId, OrderStates state) {
        OrderTraceability orderTraceability = persistencePort.getOrderTraceByOrderId(orderId);

        orderTraceability.setAssignedEmployeeId(employeeId);
        orderTraceability.getOrderTracking().add(new OrderTrackingModel(state, Instant.now()));

        persistencePort.saveOrderTrace(orderTraceability);

    }

    @Override
    public OrderTraceability listOrderTrace(Long orderId) {
        return persistencePort.getOrderTraceByOrderId(orderId);
    }
}
