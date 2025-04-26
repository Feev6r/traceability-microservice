package ferv.dev.traceabilitymicroservice.usecases;

import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTrackingModel;
import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.OrderTraceabilityPersistencePort;
import ferv.dev.traceabilitymicroservice.category.domain.usecases.OrderTraceabilityUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderTraceabilityUseCaseTest {

    @InjectMocks
    private OrderTraceabilityUseCase orderTraceabilityUseCase;

    @Mock
    private OrderTraceabilityPersistencePort persistencePort;

    private OrderTraceability orderTraceability1;
    private OrderTraceability orderTraceability2;

    @BeforeEach
    void setUp(){
        List<OrderTrackingModel> orderTrackingModelList1 = new ArrayList<>();
        orderTrackingModelList1.add(new OrderTrackingModel(OrderStates.PENDING, null));

        List<OrderTrackingModel> orderTrackingModelList2 = new ArrayList<>();
        orderTrackingModelList2.add(new OrderTrackingModel(OrderStates.PENDING, Instant.now()));

        orderTraceability1 = new OrderTraceability(null, null, 1L, 1L, orderTrackingModelList1);
        orderTraceability2 = new OrderTraceability("ABC1ABC2", 1L, 1L, 1L, orderTrackingModelList2);
    }

    @Test
    void createOrderTraceTest(){

        List<OrderTrackingModel> changedOrderTrackingList = new ArrayList<>();
        changedOrderTrackingList.add(new OrderTrackingModel(OrderStates.PENDING, Instant.now()));

        orderTraceabilityUseCase.createOrderTrace(orderTraceability1);

        verify(persistencePort).saveOrderTrace(orderTraceability1);

        assertThat(orderTraceability1.getOrderTracking()).isEqualTo(changedOrderTrackingList);
    }

    @Test
    void orderUpdateTraceTest(){

        long orderId = 1L;
        OrderStates state = OrderStates.READY;

        when(persistencePort.getOrderTraceByOrderId(orderId)).thenReturn(orderTraceability2);
        orderTraceabilityUseCase.orderUpdateTrace(orderId, state);

        verify(persistencePort).saveOrderTrace(orderTraceability2);

        //verify that the orderTrackingModel was added to the object
        assertThat(orderTraceability2.getOrderTracking().getLast().getState()).isEqualTo(state);
    }

    @Test
    void updateOrderTraceByAssigningEmployeeTest(){
        long orderId = 1L;
        long employeeId = 1L;
        OrderStates state = OrderStates.PREPARING;
        when(persistencePort.getOrderTraceByOrderId(orderId)).thenReturn(orderTraceability1);

        orderTraceabilityUseCase.updateOrderTraceByAssigningEmployee(employeeId, orderId,  state);

        verify(persistencePort).saveOrderTrace(orderTraceability1);

        assertThat(orderTraceability1.getAssignedEmployeeId()).isEqualTo(employeeId);
        assertThat(orderTraceability1.getOrderTracking().getLast().getState()).isEqualTo(state);
    }


    @Test
    void listOrderTraceTest(){
        long orderId = 1L;
        when(persistencePort.getOrderTraceByOrderId(orderId)).thenReturn(orderTraceability2);

        OrderTraceability orderTraceability = orderTraceabilityUseCase.listOrderTrace(orderId);

        assertThat(orderTraceability).isEqualTo(orderTraceability2);

    }
}
