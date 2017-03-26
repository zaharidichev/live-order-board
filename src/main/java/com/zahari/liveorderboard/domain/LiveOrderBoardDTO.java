package com.zahari.liveorderboard.domain;

import java.util.List;

/**
 * Created by zahari on 26/03/2017.
 */
public class LiveOrderBoardDTO {

    private List<PriceLevelDTO> sellOrders;
    private List<PriceLevelDTO> buyOrders;

    public LiveOrderBoardDTO() {
    }

    public LiveOrderBoardDTO(List<PriceLevelDTO> sellOrders, List<PriceLevelDTO> buyOrders) {
        this.sellOrders = sellOrders;
        this.buyOrders = buyOrders;
    }

    public List<PriceLevelDTO> getSellOrders() {
        return sellOrders;
    }

    public List<PriceLevelDTO> getBuyOrders() {
        return buyOrders;
    }
}
