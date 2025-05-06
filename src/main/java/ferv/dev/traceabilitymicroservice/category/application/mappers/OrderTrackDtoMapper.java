package ferv.dev.traceabilitymicroservice.category.application.mappers;

import ferv.dev.traceabilitymicroservice.category.application.dto.response.OrderTrackResponse;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTrackingModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderTrackDtoMapper {

    OrderTrackResponse toOrderTrackResponse(OrderTrackingModel orderTrackingModel);
}
