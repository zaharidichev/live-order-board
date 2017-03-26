package com.zahari.liveorderboard.domain.dto;

import java.util.Set;

/**
 *
 * A price level is the result of one or more orders of the same price
 * that have the same market side. The quantity is a sum of all the orders.
 * In order to be able to look at individual orders, a set of the Ids is provided.
 *
 * Created by zahari on 26/03/2017.
 */
public class PriceLevelDTO {

    private  Double quantityInKg;
    private  Double pricePerKg;
    private MarketSide side;
    private Set<String> orderIds;

    public PriceLevelDTO() {
    }

    public PriceLevelDTO(Double quantityInKg, Double pricePerKg, MarketSide side, Set<String> orderIds) {
        this.quantityInKg = quantityInKg;
        this.pricePerKg = pricePerKg;
        this.side = side;
        this.orderIds = orderIds;
    }


    public Double getQuantityInKg() {
        return quantityInKg;
    }

    public Double getPricePerKg() {
        return pricePerKg;
    }

    public MarketSide getSide() {
        return side;
    }

    public Set<String> getOrderIds() {
        return orderIds;
    }
}
