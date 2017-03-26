package com.zahari.liveorderboard;

import com.zahari.liveorderboard.domain.MarketSide;
import com.zahari.liveorderboard.domain.OrderDTO;
import com.zahari.liveorderboard.error.OrderNotFoundException;
import com.zahari.liveorderboard.repository.OrderEntityRepository;
import com.zahari.liveorderboard.service.IOrderService;
import com.zahari.liveorderboard.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by zahari on 26/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LiveOrderBoardApplication.class)
public class OrderServiceTest {


    @Autowired
    private OrderEntityRepository repo;
    private IOrderService orderService;

    @Before
    public void setup() {
        this.repo.deleteAll();
        assert(this.repo.count() == 0);
        this.orderService = new OrderService(repo);
    }


    @Test
    public void shouldBeAbleToCreateAnOrder() {
        OrderDTO orderToCreate = new OrderDTO(null,"dummyUserId",1.1,2.5, MarketSide.BUY);
        OrderDTO createdOrder = this.orderService.createOrder(orderToCreate);

        assertEquals(orderToCreate.getUserId(),createdOrder.getUserId());
        assertEquals(orderToCreate.getQuantityInKg(),createdOrder.getQuantityInKg());
        assertEquals(orderToCreate.getPricePerKg(),createdOrder.getPricePerKg());
        assertEquals(orderToCreate.getSide(),createdOrder.getSide());
        assertTrue(createdOrder.getOrderId() != null);
    }


    @Test
    public void shouldBeAbleToObtainAnOrderById() {
        OrderDTO orderToCreate = new OrderDTO(null,"dummyUserId",1.1,2.5, MarketSide.BUY);
        String createdOrderId = this.orderService.createOrder(orderToCreate).getOrderId();

        OrderDTO foundOrder = this.orderService.getOrder(createdOrderId);
        assertEquals(orderToCreate.getUserId(),foundOrder.getUserId());
        assertEquals(orderToCreate.getQuantityInKg(),foundOrder.getQuantityInKg());
        assertEquals(orderToCreate.getPricePerKg(),foundOrder.getPricePerKg());
        assertEquals(orderToCreate.getSide(),foundOrder.getSide());
        assertTrue(foundOrder.getOrderId() != null);
    }


    @Test(expected=OrderNotFoundException.class)
    public void tryingToObtainNonExistingOrderShouldThrowAnException() {
        this.orderService.getOrder("notThere");
    }

    @Test(expected=OrderNotFoundException.class)
    public void tryingToCancelNonExistingOrderShouldThrowAnException() {
        this.orderService.cancelOrder("notThere");
    }

}
