package ferv.dev.traceabilitymicroservice.category.infrastructure.mappers;

import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.infrastructure.documents.mongodb.OrderTraceabilityDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderTraceabilityMapper {

    OrderTraceabilityDocument toDocument(OrderTraceability orderTraceability);
    OrderTraceability toModel(OrderTraceabilityDocument orderTraceabilityDocument);

    List<OrderTraceability> toModelList(List<OrderTraceabilityDocument> orderTraceabilityDocumentList);

}
