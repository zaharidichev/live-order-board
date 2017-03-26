package com.zahari.liveorderboard.service;

import com.zahari.liveorderboard.domain.dto.LiveOrderBoardDTO;
import com.zahari.liveorderboard.domain.dto.PriceLevelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collections;
import java.util.List;

import static com.zahari.liveorderboard.service.PriceLevelCollectorFactory.toBuySideLevels;
import static com.zahari.liveorderboard.service.PriceLevelCollectorFactory.toSellSideLevels;

/**
 * Created by zahari on 26/03/2017.
 */
@Service
public class LiveOrderBoardService implements ILiveOrderBoardService{

    private IOrderService orderService;

    @Autowired
    public LiveOrderBoardService(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public LiveOrderBoardDTO getLiveOrderBoard() {
        List<PriceLevelDTO> sellSideLevels = orderService.getSellOrders().collect(toSellSideLevels());
        List<PriceLevelDTO> buySideLevels = orderService.getBuyOrders().collect(toBuySideLevels());
        return new LiveOrderBoardDTO(sellSideLevels,buySideLevels);
    }
}
