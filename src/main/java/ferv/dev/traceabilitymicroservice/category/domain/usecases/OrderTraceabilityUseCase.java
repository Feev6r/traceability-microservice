package ferv.dev.traceabilitymicroservice.category.domain.usecases;

import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTrackingModel;
import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;
import ferv.dev.traceabilitymicroservice.category.domain.ports.in.EmployeeTraceabilityPort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.in.OrderTraceabilityPort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.OrderTraceabilityPersistencePort;

import java.time.Duration;
import java.time.Instant;

public class OrderTraceabilityUseCase implements OrderTraceabilityPort {

    private final OrderTraceabilityPersistencePort persistencePort;
    private final EmployeeTraceabilityPort employeeTraceabilityPort;

    public OrderTraceabilityUseCase(OrderTraceabilityPersistencePort persistencePort, EmployeeTraceabilityPort employeeTraceabilityPort) {
        this.persistencePort = persistencePort;
        this.employeeTraceabilityPort = employeeTraceabilityPort;
    }

    @Override
    public void createOrderTrace(OrderTraceability orderTraceability) {
        //Set the timestamp on the last record which is the one just created
        orderTraceability.getOrderTrack().getLast().setTimeStamp(Instant.now());

        persistencePort.saveOrderTrace(orderTraceability);
    }

    @Override
    public void orderUpdateTrace(Long orderId, OrderStates state) {
        OrderTraceability orderTraceability = persistencePort.getOrderTraceByOrderId(orderId);
        orderTraceability.getOrderTrack().add(new OrderTrackingModel(state, Instant.now()));

        if(state == OrderStates.READY){
            employeeTraceabilityPort.calculateAverageEfficiency(orderTraceability.getAssignedEmployeeId());
        }

        if(state == OrderStates.DELIVERED){
            Duration duration = Duration.between(
                    orderTraceability.getOrderTrack().getFirst().getTimeStamp(),
                    orderTraceability.getOrderTrack().getLast().getTimeStamp());

            orderTraceability.setOrderDurationInMilliseconds(duration.toMillis());
        }

        persistencePort.saveOrderTrace(orderTraceability);

    }

    @Override
    public void updateOrderTraceByAssigningEmployee(Long employeeId, Long orderId, OrderStates state) {
        OrderTraceability orderTraceability = persistencePort.getOrderTraceByOrderId(orderId);

        orderTraceability.setAssignedEmployeeId(employeeId);
        orderTraceability.getOrderTrack().add(new OrderTrackingModel(state, Instant.now()));


        employeeTraceabilityPort.createEmployeeTrace(employeeId);

        persistencePort.saveOrderTrace(orderTraceability);

    }

    @Override
    public OrderTraceability getOrderTrace(Long orderId) {
        return persistencePort.getOrderTraceByOrderId(orderId);
    }
}
