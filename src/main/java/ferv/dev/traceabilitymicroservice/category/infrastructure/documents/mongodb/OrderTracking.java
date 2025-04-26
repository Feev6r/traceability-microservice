package ferv.dev.traceabilitymicroservice.category.infrastructure.documents.mongodb;

import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderTracking {

    private Instant timeStamp;
    private OrderStates state;

}
