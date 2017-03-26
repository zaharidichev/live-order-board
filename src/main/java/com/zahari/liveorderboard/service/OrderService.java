package com.zahari.liveorderboard.service;

import com.zahari.liveorderboard.domain.OrderDTO;
import com.zahari.liveorderboard.repository.OrderEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by zahari on 26/03/2017.
 */
@Service
public class OrderService implements IOrderService{

    private OrderEntityRepository repo;

    @Autowired
    public OrderService(OrderEntityRepository repo) {
        this.repo = repo;
    }

    @Override
    public OrderDTO createOrder(OrderDTO order) {
        throw new NotImplementedException();
    }

    @Override
    public void cancelOrder(String orderId) {
        throw new NotImplementedException();

    }

    @Override
    public OrderDTO getOrder(String orderId) {
        throw new NotImplementedException();
    }
}
