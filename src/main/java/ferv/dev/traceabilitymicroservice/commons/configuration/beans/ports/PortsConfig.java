package ferv.dev.traceabilitymicroservice.commons.configuration.beans.ports;

import ferv.dev.traceabilitymicroservice.category.domain.ports.in.OrderTraceabilityPort;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.OrderTraceabilityPersistencePort;
import ferv.dev.traceabilitymicroservice.category.domain.usecases.OrderTraceabilityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PortsConfig {

    private final OrderTraceabilityPersistencePort orderTraceabilityPersistencePort;

    @Bean
    public OrderTraceabilityPort orderTraceabilityPort(){
        return new OrderTraceabilityUseCase(orderTraceabilityPersistencePort);
    }

}
