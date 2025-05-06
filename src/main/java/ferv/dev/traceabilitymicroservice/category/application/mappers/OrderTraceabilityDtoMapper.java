package ferv.dev.traceabilitymicroservice.category.application.mappers;

import ferv.dev.traceabilitymicroservice.category.application.dto.request.OrderTraceabilityRequest;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTrackingModel;
import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderTraceabilityDtoMapper {

    @Mapping(target = "orderTrack", expression = "java(toTrackingModel(request.state()))")
    OrderTraceability toModel(OrderTraceabilityRequest request);


    default List<OrderTrackingModel> toTrackingModel(OrderStates state){
        return List.of(new OrderTrackingModel(state, null));
    }

}
