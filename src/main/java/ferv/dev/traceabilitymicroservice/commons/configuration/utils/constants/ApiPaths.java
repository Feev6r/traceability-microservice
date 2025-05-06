package ferv.dev.traceabilitymicroservice.commons.configuration.utils.constants;

public class ApiPaths {
    private ApiPaths() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ORDER_TRACE = "/order-trace";
    public static final String EMPLOYEE_TRACE = "/employee-trace";
    public static final String CREATE = "/create";
    public static final String UPDATE = "/update";
    public static final String EMPLOYEE = "/employee";
    public static final String ID = "/{id}";
}
