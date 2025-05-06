package ferv.dev.traceabilitymicroservice.usecases;

import ferv.dev.traceabilitymicroservice.category.domain.models.EmployeeTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTrackingModel;
import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.EmployeeTraceabilityPersistencePort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.OrderTraceabilityPersistencePort;
import ferv.dev.traceabilitymicroservice.category.domain.usecases.EmployeeTraceabilityUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeTraceabilityUseCaseTest {

    @Mock
    private EmployeeTraceabilityPersistencePort employeeTraceabilityPersistencePort;

    @Mock
    private OrderTraceabilityPersistencePort orderTraceabilityPersistencePort;

    @InjectMocks
    private EmployeeTraceabilityUseCase employeeTraceabilityUseCase;

    private List<OrderTraceability> orderTraceabilityList1;
    private EmployeeTraceability employeeTraceability1;

    @BeforeEach
    void setUp(){

        Duration twoHours = Duration.ofHours(2);
        Duration sixHours = Duration.ofHours(6);
        Instant instant = Instant.now();

        List<OrderTrackingModel> orderTrackingModelList1 = new ArrayList<>();
        orderTrackingModelList1.add(new OrderTrackingModel(OrderStates.PENDING, Instant.now()));
        orderTrackingModelList1.add(new OrderTrackingModel(OrderStates.READY, instant.plus(twoHours)));

        List<OrderTrackingModel> orderTrackingModelList2 = new ArrayList<>();
        orderTrackingModelList2.add(new OrderTrackingModel(OrderStates.PENDING, Instant.now()));
        orderTrackingModelList2.add(new OrderTrackingModel(OrderStates.READY, instant.plus(sixHours)));

        OrderTraceability orderTraceability1 = new OrderTraceability("ABC2ABC3", 1L, 1L, 1L, null, orderTrackingModelList1);
        OrderTraceability orderTraceability2 = new OrderTraceability("ABC4ABC6", 1L, 1L, 1L, null, orderTrackingModelList2);

        orderTraceabilityList1 = Arrays.asList(orderTraceability1, orderTraceability2);

        employeeTraceability1 = new EmployeeTraceability("ABC1ABC2", 2L, null);
    }

    @Test
    void listEmployeeTraceTest(){
        List<Long> employeeIds = Arrays.asList(1L, 2L, 3L);

        employeeTraceabilityUseCase.listEmployeTrace(0, 1, false, employeeIds);

        verify(employeeTraceabilityPersistencePort).listEmployeeTrace(0, 1, false, employeeIds);

    }

    @Test
    void calculateAverageEfficiencyTest(){
        Long assignedEmployeeId = 1L;
        long toltalMillisecondsSum = 0L;
        Long tolerance = 1000L;

        for (OrderTraceability orderTraceability : orderTraceabilityList1){
            Duration duration = Duration.between(
                    orderTraceability.getOrderTrack().getFirst().getTimeStamp(),
                    orderTraceability.getOrderTrack().getLast().getTimeStamp());

            toltalMillisecondsSum += duration.toMillis();
        }

        Long expectedAverageEfficiency = toltalMillisecondsSum / orderTraceabilityList1.size();

        when(orderTraceabilityPersistencePort.listOrderTraceabilityByAssignedEmployee(assignedEmployeeId)).thenReturn(orderTraceabilityList1);
        when(employeeTraceabilityPersistencePort.getEmployeeTraceByEmployee(assignedEmployeeId)).thenReturn(employeeTraceability1);

        employeeTraceabilityUseCase.calculateAverageEfficiency(assignedEmployeeId);

        verify(employeeTraceabilityPersistencePort).saveEmployeeTrace(employeeTraceability1);

        assertThat(employeeTraceability1.getAverageEfficiencyInMilliseconds()).isCloseTo(expectedAverageEfficiency, within(tolerance));
    }

    @Test
    void createEmployeeTraceTest(){
        Long employeeId = 1L;

        employeeTraceabilityUseCase.createEmployeeTrace(employeeId);

        verify(employeeTraceabilityPersistencePort).saveEmployeeTrace(
                new EmployeeTraceability(null, employeeId, null));
    }

}
