package com.zahari.liveorderboard.endpoint;

import com.zahari.liveorderboard.domain.dto.LiveOrderBoardDTO;
import com.zahari.liveorderboard.service.ILiveOrderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zahari on 26/03/2017.
 */
@RestController
@RequestMapping("/api/live-order-board")
public class OrderBoardEndpoint {

    private ILiveOrderBoardService liveOrderBoardService;

    @Autowired
    public OrderBoardEndpoint(ILiveOrderBoardService liveOrderBoardService) {
        this.liveOrderBoardService = liveOrderBoardService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public LiveOrderBoardDTO getOrderBoard() {
        return liveOrderBoardService.getLiveOrderBoard();
    }
}
