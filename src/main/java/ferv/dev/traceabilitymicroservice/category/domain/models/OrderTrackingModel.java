package ferv.dev.traceabilitymicroservice.category.domain.models;

import ferv.dev.traceabilitymicroservice.category.domain.models.enums.OrderStates;

import java.time.Instant;
import java.util.Objects;

public class OrderTrackingModel {

    private Instant timeStamp;
    private OrderStates state;

    public OrderTrackingModel(OrderStates state, Instant timeStamp) {
        this.state = state;
        this.timeStamp = timeStamp;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public OrderStates getState() {
        return state;
    }

    public void setState(OrderStates state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderTrackingModel that = (OrderTrackingModel) o;
        return Objects.equals(timeStamp, that.timeStamp) && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, state);
    }
}
