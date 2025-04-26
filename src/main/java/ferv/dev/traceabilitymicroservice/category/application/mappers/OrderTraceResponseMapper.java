package ferv.dev.traceabilitymicroservice.category.application.mappers;

import ferv.dev.traceabilitymicroservice.category.application.dto.response.OrderTraceResponse;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTraceability;
import ferv.dev.traceabilitymicroservice.category.domain.models.OrderTrackingModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderTraceResponseMapper {


    public OrderTraceResponse toOrderTraceResponse(OrderTrackingModel orderTrackingModel){
        return new OrderTraceResponse(orderTrackingModel.getTimeStamp(), orderTrackingModel.getState());
    }

    public List<OrderTraceResponse> toOrderTraceResponseList(OrderTraceability orderTraceability){
        List<OrderTraceResponse> orderTraceResponseList = new ArrayList<>();
        for(OrderTrackingModel orderTrackingModel : orderTraceability.getOrderTracking()){
            orderTraceResponseList.add(toOrderTraceResponse(orderTrackingModel));
        }
        return  orderTraceResponseList;
    }

}
