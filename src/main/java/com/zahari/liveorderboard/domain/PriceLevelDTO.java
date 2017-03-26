package com.zahari.liveorderboard.domain;

import java.util.List;
import java.util.Set;

/**
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
