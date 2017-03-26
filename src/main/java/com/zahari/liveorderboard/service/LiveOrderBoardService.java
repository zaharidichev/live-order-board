package com.zahari.liveorderboard.service;

import com.zahari.liveorderboard.domain.LiveOrderBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException();
    }
}
