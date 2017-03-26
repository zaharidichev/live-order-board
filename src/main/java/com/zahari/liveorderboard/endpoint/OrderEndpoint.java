package com.zahari.liveorderboard.endpoint;

import com.zahari.liveorderboard.domain.OrderDTO;
import com.zahari.liveorderboard.repository.OrderEntityRepository;
import com.zahari.liveorderboard.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by zahari on 26/03/2017.
 */
@RestController
@RequestMapping("/api/order")
public class OrderEndpoint {

    private IOrderService orderService;

    @Autowired
    public OrderEndpoint(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public OrderDTO createOrder(@RequestBody OrderDTO order) {
        return orderService.createOrder(order);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrderDTO getOrder(@PathVariable("id") String id) {
        return orderService.getOrder(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void cancelOrder(@PathVariable("id") String id) {
        this.orderService.cancelOrder(id);
    }

}
