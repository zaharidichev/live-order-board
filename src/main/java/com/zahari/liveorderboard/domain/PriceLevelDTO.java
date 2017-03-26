package com.zahari.liveorderboard.domain;

import java.util.List;

/**
 * Created by zahari on 26/03/2017.
 */
public class PriceLevelDTO {
    private Double price;
    private MarketSide side;
    private List<String> orderIds;

    public PriceLevelDTO() {
    }

    public PriceLevelDTO(Double price, MarketSide side, List<String> orderIds) {
        this.price = price;
        this.side = side;
        this.orderIds = orderIds;
    }

    public Double getPrice() {
        return price;
    }

    public MarketSide getSide() {
        return side;
    }

    public List<String> getOrderIds() {
        return orderIds;
    }
}
