package com.zahari.liveorderboard.service;

import com.zahari.liveorderboard.domain.OrderDTO;

/**
 * Created by zahari on 26/03/2017.
 */
public interface IOrderService {
    OrderDTO createOrder(OrderDTO order);
    void cancelOrder(String orderId);
    OrderDTO getOrder(String orderId);
}
