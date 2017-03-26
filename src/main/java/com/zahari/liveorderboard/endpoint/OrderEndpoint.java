package com.zahari.liveorderboard.endpoint;

import com.zahari.liveorderboard.domain.OrderDTO;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by zahari on 26/03/2017.
 */
@RestController
@RequestMapping("/api/order")
public class OrderEndpoint {

    @RequestMapping(method = RequestMethod.POST)
    public OrderDTO createOrder(@RequestBody OrderDTO order) {
        throw new NotImplementedException();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrderDTO getOrder(@PathVariable("id") String id) {
        throw new NotImplementedException();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void cancelOrder(@PathVariable("id") String id) {
        throw new NotImplementedException();
    }

}
