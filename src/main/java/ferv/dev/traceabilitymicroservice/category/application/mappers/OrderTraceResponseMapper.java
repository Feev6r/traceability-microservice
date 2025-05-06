package ferv.dev.traceabilitymicroservice.category.application.mappers;

import ferv.dev.traceabilitymicroservice.category.application.dto.response.OrderTraceResponse;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = OrderTrackDtoMapper.class)
public interface OrderTraceResponseMapper {
    OrderTraceResponse toOrderTraceResponse(OrderTraceability orderTraceability);
}
