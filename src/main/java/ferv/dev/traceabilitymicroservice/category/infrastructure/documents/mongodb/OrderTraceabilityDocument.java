package ferv.dev.traceabilitymicroservice.category.infrastructure.documents.mongodb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders_traceability")
public class OrderTraceabilityDocument {

    @Id
    private String id;
    private String assignedEmployeeId;
    private String clientId;
    private String orderId;

    private List<OrderTracking> orderTracking = new ArrayList<>();
}
