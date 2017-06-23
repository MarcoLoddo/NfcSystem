package it.extrasys.tesi.tagsystem.customer_consult_service.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
