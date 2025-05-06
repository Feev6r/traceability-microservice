package ferv.dev.traceabilitymicroservice.category.infrastructure.endpoints.res;

import ferv.dev.traceabilitymicroservice.category.application.dto.request.EmployeeIdsRequest;
import ferv.dev.traceabilitymicroservice.category.application.dto.response.EmployeeTraceResponse;
import ferv.dev.traceabilitymicroservice.category.application.services.EmployeeTraceabilityService;
import ferv.dev.traceabilitymicroservice.commons.configuration.utils.constants.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.EMPLOYEE_TRACE)
@RequiredArgsConstructor
@Validated
public class EmployeeTraceabilityController {

    private final EmployeeTraceabilityService employeeTraceabilityService;

    @PostMapping
    public ResponseEntity<List<EmployeeTraceResponse>> listEmployeeTrace(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "false")boolean orderAsc,
            @RequestBody EmployeeIdsRequest request){

        return ResponseEntity.ok(employeeTraceabilityService.listEmployeeTrace(page, size, orderAsc, request.employeeIds()));
    }

}
