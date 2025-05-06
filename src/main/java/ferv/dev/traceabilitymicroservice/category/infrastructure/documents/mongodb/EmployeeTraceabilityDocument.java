package ferv.dev.traceabilitymicroservice.category.infrastructure.documents.mongodb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee_traceability")
public class EmployeeTraceabilityDocument {

    @Id
    private String id;
    private String employeeId;
    private Long averageEfficiencyInMilliseconds;

}
