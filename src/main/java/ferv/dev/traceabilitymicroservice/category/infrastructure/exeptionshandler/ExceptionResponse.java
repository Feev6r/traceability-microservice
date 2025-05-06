package ferv.dev.traceabilitymicroservice.category.infrastructure.exeptionshandler;

public enum ExceptionResponse {

    EMPLOYEE_TRACE_NOT_FOUND("Employee trace was not found"),
    ORDER_TRACE_NOT_FOUND("Order trace was not found");

    private final String message;

    ExceptionResponse(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
