package ferv.dev.traceabilitymicroservice.category.infrastructure.adapters.persistence;

import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.ports.out.OrderTraceabilityPersistencePort;
import ferv.dev.traceabilitymicroservice.category.infrastructure.mappers.OrderTraceabilityMapper;
import ferv.dev.traceabilitymicroservice.category.infrastructure.repositories.mongodb.OrderTraceabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderTraceabilityAdapter implements OrderTraceabilityPersistencePort {

    private final OrderTraceabilityRepository repository;
    private final OrderTraceabilityMapper orderTraceabilityMapper;


    @Override
    public OrderTraceability saveOrderTrace(OrderTraceability orderTraceability) {
       return orderTraceabilityMapper.toModel(
               repository.save(orderTraceabilityMapper.toDocument(orderTraceability)));
    }

    @Override
    public OrderTraceability getOrderTraceByOrderId(Long orderId) {
        return orderTraceabilityMapper.toModel(repository.findByOrderId(orderId.toString())
                .orElseThrow(RuntimeException::new));
    }
}
