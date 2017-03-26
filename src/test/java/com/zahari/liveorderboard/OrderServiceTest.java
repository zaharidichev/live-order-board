package com.zahari.liveorderboard;

import com.zahari.liveorderboard.domain.dto.MarketSide;
import com.zahari.liveorderboard.domain.dto.OrderDTO;
import com.zahari.liveorderboard.error.exception.OrderNotFoundException;
import com.zahari.liveorderboard.repository.OrderEntityRepository;
import com.zahari.liveorderboard.service.IOrderService;
import com.zahari.liveorderboard.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.stream.Collectors.toSet;
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

    @Test
    public void shouldBeAbleToCancel() {
        OrderDTO orderToCreate = new OrderDTO(null,"dummyUserId",1.1,2.5, MarketSide.BUY);
        String createdOrderId = this.orderService.createOrder(orderToCreate).getOrderId();
        assertTrue(this.orderService.getAllOrders().count() == 1);
        this.orderService.cancelOrder(createdOrderId);
        assertTrue(this.orderService.getAllOrders().count() == 0);

    }




    @Test
    public void shouldBeAbleToFindByBuySide() {
        OrderDTO buySideOrder = new OrderDTO(null,"dummyUserBuy",1.1,2.5, MarketSide.BUY);
        OrderDTO sellSideOrder = new OrderDTO(null,"dummyUserSell",1.1,2.5, MarketSide.SELL);

        OrderDTO createdBuyOrder = this.orderService.createOrder(buySideOrder);
        this.orderService.createOrder(sellSideOrder);

        assertTrue(this.orderService.getBuyOrders().collect(toSet()).contains(createdBuyOrder));
        assertTrue(this.orderService.getBuyOrders().count() == 1);
        assertTrue(this.orderService.getAllOrders().count() == 2);

    }

    @Test
    public void shouldBeAbleToFindBySellSide() {
        OrderDTO buySideOrder = new OrderDTO(null,"dummyUserBuy",1.1,2.5, MarketSide.BUY);
        OrderDTO sellSideOrder = new OrderDTO(null,"dummyUserSell",1.1,2.5, MarketSide.SELL);

        this.orderService.createOrder(buySideOrder);
        OrderDTO createdSellOrder = this.orderService.createOrder(sellSideOrder);

        assertTrue(this.orderService.getSellOrders().collect(toSet()).contains(createdSellOrder));
        assertTrue(this.orderService.getSellOrders().count() == 1);
        assertTrue(this.orderService.getAllOrders().count() == 2);

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
