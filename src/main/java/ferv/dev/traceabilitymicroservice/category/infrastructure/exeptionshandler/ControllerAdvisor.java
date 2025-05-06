package ferv.dev.traceabilitymicroservice.category.infrastructure.exeptionshandler;

import ferv.dev.traceabilitymicroservice.category.infrastructure.exeptionshandler.exceptions.EmployeeTraceNotFound;
import ferv.dev.traceabilitymicroservice.category.infrastructure.exeptionshandler.exceptions.OrderTraceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(EmployeeTraceNotFound.class)
    public ResponseEntity<Map<String, String>> handleEmployeeTraceNotFound(EmployeeTraceNotFound exeption){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMPLOYEE_TRACE_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(OrderTraceNotFound.class)
    public ResponseEntity<Map<String, String>> handleOrderTraceNotFound(OrderTraceNotFound exeption){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ORDER_TRACE_NOT_FOUND.getMessage()));
    }


}
