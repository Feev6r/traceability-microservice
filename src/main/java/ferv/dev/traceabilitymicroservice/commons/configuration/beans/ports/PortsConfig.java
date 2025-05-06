package ferv.dev.traceabilitymicroservice.commons.configuration.beans.ports;

import ferv.dev.traceabilitymicroservice.category.domain.ports.in.EmployeeTraceabilityPort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.in.OrderTraceabilityPort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.EmployeeTraceabilityPersistencePort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.OrderTraceabilityPersistencePort;
import ferv.dev.traceabilitymicroservice.category.domain.usecases.EmployeeTraceabilityUseCase;
import ferv.dev.traceabilitymicroservice.category.domain.usecases.OrderTraceabilityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PortsConfig {

    private final OrderTraceabilityPersistencePort orderTraceabilityPersistencePort;
    private final EmployeeTraceabilityPersistencePort employeeTraceabilityPersistencePort;

    @Bean
    public EmployeeTraceabilityPort employeeTraceabilityPort(){
        return new EmployeeTraceabilityUseCase(employeeTraceabilityPersistencePort, orderTraceabilityPersistencePort);
    }

    @Bean
    public OrderTraceabilityPort orderTraceabilityPort(){
        return new OrderTraceabilityUseCase(orderTraceabilityPersistencePort, employeeTraceabilityPort());
    }

}
