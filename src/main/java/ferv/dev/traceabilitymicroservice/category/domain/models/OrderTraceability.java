package ferv.dev.traceabilitymicroservice.category.domain.models;

import java.util.List;
import java.util.Objects;

public class OrderTraceability {

    private String id;
    private Long assignedEmployeeId;
    private Long clientId;
    private Long orderId;
    private Long orderDurationInMilliseconds;
    private List<OrderTrackingModel> orderTrack;

    public OrderTraceability(String id, Long assignedEmployeeId, Long clientId, Long orderId, Long orderDurationInMilliseconds, List<OrderTrackingModel> orderTrack) {
        this.id = id;
        this.assignedEmployeeId = assignedEmployeeId;
        this.clientId = clientId;
        this.orderId = orderId;
        this.orderDurationInMilliseconds = orderDurationInMilliseconds;
        this.orderTrack = orderTrack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(Long assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderDurationInMilliseconds() {
        return orderDurationInMilliseconds;
    }

    public void setOrderDurationInMilliseconds(Long orderDurationInMilliseconds) {
        this.orderDurationInMilliseconds = orderDurationInMilliseconds;
    }

    public List<OrderTrackingModel> getOrderTrack() {
        return orderTrack;
    }

    public void setOrderTrack(List<OrderTrackingModel> orderTrack) {
        this.orderTrack = orderTrack;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderTraceability that = (OrderTraceability) o;
        return Objects.equals(id, that.id) && Objects.equals(assignedEmployeeId, that.assignedEmployeeId) && Objects.equals(clientId, that.clientId) && Objects.equals(orderId, that.orderId) && Objects.equals(orderTrack, that.orderTrack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assignedEmployeeId, clientId, orderId, orderTrack);
    }
}
