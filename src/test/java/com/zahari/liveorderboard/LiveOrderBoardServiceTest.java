package com.zahari.liveorderboard;

import com.zahari.liveorderboard.domain.LiveOrderBoardDTO;
import com.zahari.liveorderboard.domain.MarketSide;
import com.zahari.liveorderboard.domain.OrderDTO;
import com.zahari.liveorderboard.domain.PriceLevelDTO;
import com.zahari.liveorderboard.service.ILiveOrderBoardService;
import com.zahari.liveorderboard.service.IOrderService;
import com.zahari.liveorderboard.service.LiveOrderBoardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.zahari.liveorderboard.TestingUtils.buyOrdersInDescendingOrder;
import static com.zahari.liveorderboard.TestingUtils.sellOrdersInAscendingOrder;
import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by zahari on 26/03/2017.
 */
public class LiveOrderBoardServiceTest {


    private IOrderService orderServiceMock;

    @Before
    public void setup(){

        Stream<OrderDTO> buyOrders = Stream.of(new OrderDTO("orderId1","dummyUserId",2.5,3.0, MarketSide.BUY),
                new OrderDTO("orderId2","dummyUserId",5.0,3.0, MarketSide.BUY),
                new OrderDTO("orderId3","dummyUserId",7.0,1.5, MarketSide.BUY));

        Stream<OrderDTO> sellOrders = Stream.of(new OrderDTO("orderId4","dummyUserId",1.1,5.0, MarketSide.SELL),
                new OrderDTO("orderId5","dummyUserId",10.5,5.0, MarketSide.SELL),
                new OrderDTO("orderId6","dummyUserId",22.0,1.5, MarketSide.SELL));

        orderServiceMock = Mockito.mock(IOrderService.class);
        when(orderServiceMock.getBuyOrders()).thenReturn(buyOrders);
        when(orderServiceMock.getSellOrders()).thenReturn(sellOrders);
    }


    @Test
    public void shouldReturnAnEmptyBookIfThereAreNoOrders() {
        IOrderService emptyOrderServiceMock = Mockito.mock(IOrderService.class);
        when(emptyOrderServiceMock.getBuyOrders()).thenReturn(Stream.empty());
        when(emptyOrderServiceMock.getSellOrders()).thenReturn(Stream.empty());

        ILiveOrderBoardService orderBoardService = new LiveOrderBoardService(emptyOrderServiceMock);
        assertTrue(orderBoardService.getLiveOrderBoard().getBuyOrders().isEmpty());
        assertTrue(orderBoardService.getLiveOrderBoard().getSellOrders().isEmpty());

    }

    @Test
    public void shouldAggregateBuyOrders() {
        ILiveOrderBoardService orderBoardService = new LiveOrderBoardService(orderServiceMock);
        LiveOrderBoardDTO orderBoard  = orderBoardService.getLiveOrderBoard();

        Map<Double,PriceLevelDTO> priceToPriceLevel = orderBoard.getBuyOrders().stream().collect(toMap(PriceLevelDTO::getPricePerKg, Function.identity()));


        PriceLevelDTO dtoForPriceOfThree = priceToPriceLevel.get(3.0);
        Set<String> orderIdsForPriceOfThree = dtoForPriceOfThree.getOrderIds();

        assertTrue(orderIdsForPriceOfThree.size() == 2);
        assertTrue(orderIdsForPriceOfThree.contains("orderId1"));
        assertTrue(orderIdsForPriceOfThree.contains("orderId2"));
        assertTrue(dtoForPriceOfThree.getSide() == MarketSide.BUY);
        assertTrue(dtoForPriceOfThree.getPricePerKg() == 3.0);
        assertTrue(dtoForPriceOfThree.getQuantityInKg() == 7.5);



        PriceLevelDTO dtoForPriceOfOneAndAHalf = priceToPriceLevel.get(1.5);
        Set<String> orderIdsForPriceOfOneAndAHalf = dtoForPriceOfOneAndAHalf.getOrderIds();

        assertTrue(orderIdsForPriceOfOneAndAHalf.size() == 1);
        assertTrue(orderIdsForPriceOfOneAndAHalf.contains("orderId3"));
        assertTrue(dtoForPriceOfOneAndAHalf.getSide() == MarketSide.BUY);
        assertTrue(dtoForPriceOfOneAndAHalf.getPricePerKg() == 1.5);
        assertTrue(dtoForPriceOfOneAndAHalf.getQuantityInKg() == 7.0);

    }

    public static boolean isSorted(int[] data, boolean ascending){
        for(int i = 1; i < data.length; i++){
            if(data[i-1] > data[i]){
                return false;
            }
        }
        return true;
    }

    @Test
    public void shouldAggregateSellOrders() {

        ILiveOrderBoardService orderBoardService = new LiveOrderBoardService(orderServiceMock);
        LiveOrderBoardDTO orderBoard  = orderBoardService.getLiveOrderBoard();

        Map<Double,PriceLevelDTO> priceToPriceLevel = orderBoard.getSellOrders().stream().collect(toMap(PriceLevelDTO::getPricePerKg, Function.identity()));

        PriceLevelDTO dtoForPriceOfFive = priceToPriceLevel.get(5.0);
        Set<String> orderIdsForPriceOfFive = dtoForPriceOfFive.getOrderIds();


        assertTrue(orderIdsForPriceOfFive.size() == 2);
        assertTrue(orderIdsForPriceOfFive.contains("orderId4"));
        assertTrue(orderIdsForPriceOfFive.contains("orderId5"));
        assertTrue(dtoForPriceOfFive.getSide() == MarketSide.SELL);
        assertTrue(dtoForPriceOfFive.getPricePerKg() == 5.0);
        assertTrue(dtoForPriceOfFive.getQuantityInKg() == 11.6);



        PriceLevelDTO dtoForPriceOfOneAndAHalf = priceToPriceLevel.get(1.5);
        Set<String> orderIdsForPriceOfOneAndAHalf= dtoForPriceOfFive.getOrderIds();

        assertTrue(orderIdsForPriceOfOneAndAHalf.size() == 1);
        assertTrue(orderIdsForPriceOfOneAndAHalf.contains("orderId6"));
        assertTrue(dtoForPriceOfOneAndAHalf.getSide() == MarketSide.SELL);
        assertTrue(dtoForPriceOfOneAndAHalf.getPricePerKg() == 1.5);
        assertTrue(dtoForPriceOfOneAndAHalf.getQuantityInKg() == 22.0);

    }

    @Test
    public void sellOrdersShouldBeSortedByPriceInAscendingOrder() {
        ILiveOrderBoardService orderBoardService = new LiveOrderBoardService(orderServiceMock);
        assertThat(orderBoardService.getLiveOrderBoard().getSellOrders(),sellOrdersInAscendingOrder());
    }

    @Test
    public void buyOrdersShouldBeSortedByPriceInDescendingOrder() {
        ILiveOrderBoardService orderBoardService = new LiveOrderBoardService(orderServiceMock);
        assertThat(orderBoardService.getLiveOrderBoard().getSellOrders(),buyOrdersInDescendingOrder());
    }

}
