package it.extrasys.tesi.tagsystem.customer_consult_service.db.manager;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.extrasys.tesi.tagsystem.common.client.OrderDetailDto;
import it.extrasys.tesi.tagsystem.common.client.OrderDto;
import it.extrasys.tesi.tagsystem.customer_consult_service.api.RestClient;

@Component
public class ConsultManagerImpl implements ConsultManager {

    @Autowired
    private RestClient restClient;

    @Override
    @Transactional
    public List<OrderDto> getOrdersByNfc(String userNfc) {

        return this.restClient.getOrderByNfc(userNfc);

    }
    @Override
    @Transactional
    public OrderDetailDto getOrderDetailById(Long id) {
        OrderDto orderDto = this.restClient.getOrderById(id);
        OrderDetailDto orderDetail = new OrderDetailDto();
        orderDetail.setNfcId(orderDto.getNfcId());
        orderDetail.setOrderId(orderDto.getOrderId());
        orderDto.getMealId().forEach(mealId -> orderDetail.getMeals()
                .add(this.restClient.getMeal(mealId)));
        orderDetail.setTotalPrice(orderDto.getTotalPrice());
        return orderDetail;
    }
    @Override
    @Transactional
    public List<OrderDto> getOrdersByNfc(String userNfc, Date date) {
        return this.restClient.getOrderByNfc(userNfc).parallelStream()
                .filter(order -> DateUtils.isSameDay(order.getData(), date))
                .collect(Collectors.toList());
    }

}
