package ferv.dev.traceabilitymicroservice.category.domain.models;

import java.util.Objects;

public class EmployeeTraceability {
    private String id;
    private Long employeeId;
    private Long averageEfficiencyInMilliseconds;

    public EmployeeTraceability(String id, Long employeeId, Long averageEfficiencyInMilliseconds) {
        this.id = id;
        this.employeeId = employeeId;
        this.averageEfficiencyInMilliseconds = averageEfficiencyInMilliseconds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAverageEfficiencyInMilliseconds() {
        return averageEfficiencyInMilliseconds;
    }

    public void setAverageEfficiencyInMilliseconds(Long averageEfficiencyInMilliseconds) {
        this.averageEfficiencyInMilliseconds = averageEfficiencyInMilliseconds;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeTraceability that = (EmployeeTraceability) o;
        return Objects.equals(id, that.id) && Objects.equals(employeeId, that.employeeId) && Objects.equals(averageEfficiencyInMilliseconds, that.averageEfficiencyInMilliseconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, averageEfficiencyInMilliseconds);
    }
}
