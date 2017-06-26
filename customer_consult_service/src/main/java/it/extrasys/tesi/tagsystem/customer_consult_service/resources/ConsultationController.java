package it.extrasys.tesi.tagsystem.customer_consult_service.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.extrasys.tesi.tagsystem.common.client.OrderDetailDto;
import it.extrasys.tesi.tagsystem.common.client.OrderDto;
import it.extrasys.tesi.tagsystem.customer_consult_service.db.manager.ConsultManager;

@RestController
public class ConsultationController {
    @Autowired
    private ConsultManager consultManager;

    @RequestMapping(value = "/consult/{nfc}", method = RequestMethod.GET)
    public List<OrderDto> getOrders(@PathVariable String userNfc) {

        return this.consultManager.getOrdersByNfc(userNfc);
    }

    @RequestMapping(value = "/consult/{nfc}/{date}", method = RequestMethod.GET)
    public List<OrderDto> getOrdersByDate(@PathVariable String userNfc,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        return this.consultManager.getOrdersByNfc(userNfc, date);
    }

    @RequestMapping(value = "/consult/{id}", method = RequestMethod.GET)
    public OrderDetailDto getOrderDetail(@PathVariable Long id) {
        return this.consultManager.getOrderDetailById(id);
    }

}
