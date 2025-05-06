package ferv.dev.traceabilitymicroservice.category.domain.usecases;

import ferv.dev.traceabilitymicroservice.category.domain.models.EmployeeTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.ports.in.EmployeeTraceabilityPort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.EmployeeTraceabilityPersistencePort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.OrderTraceabilityPersistencePort;

import java.time.Duration;
import java.util.List;

public class EmployeeTraceabilityUseCase implements EmployeeTraceabilityPort {

    private final EmployeeTraceabilityPersistencePort persistencePort;
    private final OrderTraceabilityPersistencePort orderTraceabilityPersistencePort;

    public EmployeeTraceabilityUseCase(EmployeeTraceabilityPersistencePort persistencePort, OrderTraceabilityPersistencePort orderTraceabilityPersistencePort) {
        this.persistencePort = persistencePort;
        this.orderTraceabilityPersistencePort = orderTraceabilityPersistencePort;
    }

    @Override
    public List<EmployeeTraceability> listEmployeTrace(Integer page, Integer size, boolean orderAsc,  List<Long> employeeIds) {
        return persistencePort.listEmployeeTrace(page, size, orderAsc, employeeIds);
    }

    @Override
    public void calculateAverageEfficiency(Long assignedEmployeeId) {
        long totalMillisecondsSum = 0L;

        List<OrderTraceability> orderTraceabilityList = orderTraceabilityPersistencePort.listOrderTraceabilityByAssignedEmployee(assignedEmployeeId);

        for(OrderTraceability orderTraceability : orderTraceabilityList){

            Duration duration = Duration.between(
                    orderTraceability.getOrderTrack().getFirst().getTimeStamp(),
                    orderTraceability.getOrderTrack().getLast().getTimeStamp());

            totalMillisecondsSum += duration.toMillis();
        }

        Long averageEfficiency = totalMillisecondsSum / orderTraceabilityList.size();

        EmployeeTraceability  employeeTraceability = persistencePort.getEmployeeTraceByEmployee(assignedEmployeeId);
        employeeTraceability.setAverageEfficiencyInMilliseconds(averageEfficiency);

        persistencePort.saveEmployeeTrace(employeeTraceability);
    }

    @Override
    public EmployeeTraceability createEmployeeTrace(Long employeeId) {
        return persistencePort.saveEmployeeTrace(
                new EmployeeTraceability(null, employeeId, null));
    }
}
