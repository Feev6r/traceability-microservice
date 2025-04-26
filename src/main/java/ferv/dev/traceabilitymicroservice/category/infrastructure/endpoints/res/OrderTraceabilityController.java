package ferv.dev.traceabilitymicroservice.category.infrastructure.endpoints.res;

import ferv.dev.traceabilitymicroservice.category.application.dto.request.OrderTraceabilityRequest;
import ferv.dev.traceabilitymicroservice.category.application.dto.request.OrderTraceabilityUpdateEmployeeRequest;
import ferv.dev.traceabilitymicroservice.category.application.dto.request.OrderTraceabilityUpdateRequest;
import ferv.dev.traceabilitymicroservice.category.application.dto.response.OrderTraceResponse;
import ferv.dev.traceabilitymicroservice.category.application.services.OrderTraceabilityService;
import ferv.dev.traceabilitymicroservice.commons.configuration.utils.constants.ApiPaths;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ORDER_TRACE)
@RequiredArgsConstructor
@Validated
public class OrderTraceabilityController {

    private final OrderTraceabilityService orderTraceabilityService;

    @PostMapping(ApiPaths.CREATE)
    public ResponseEntity<Void> createOrderTrace(@Valid @RequestBody OrderTraceabilityRequest request){
        orderTraceabilityService.createOrderTrace(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(ApiPaths.EMPLOYEE)
    public ResponseEntity<Void> updateOrderTraceByAssigningEmployee(@Valid @RequestBody OrderTraceabilityUpdateEmployeeRequest request) {
        orderTraceabilityService.updateOrderTraceByAssigningEmployee(request.assignedEmployeeId(), request.orderId(), request.state());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping(ApiPaths.UPDATE)
    public ResponseEntity<Void> updateOrderTrace(@Valid @RequestBody OrderTraceabilityUpdateRequest request) {
        orderTraceabilityService.updateOrderTrace(request.orderId(), request.state());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping(ApiPaths.ID)
    public ResponseEntity<List<OrderTraceResponse>> listOrderTrace(@PathVariable Long id) {
        return ResponseEntity.ok(orderTraceabilityService.listOrderTrace(id));
    }
}
