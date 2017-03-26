package com.zahari.liveorderboard.endpoint;

import com.zahari.liveorderboard.domain.LiveOrderBoardDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by zahari on 26/03/2017.
 */
@RestController
@RequestMapping("/api/live-order-board")
public class OrderBoardEndpoint {

    @RequestMapping(method = RequestMethod.GET)
    public LiveOrderBoardDTO getOrderBoard() {
        throw new NotImplementedException();
    }
}
