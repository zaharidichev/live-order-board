package com.zahari.liveorderboard.domain.entity;

import com.zahari.liveorderboard.domain.dto.MarketSide;
import org.springframework.data.annotation.Id;

/**
 * Created by zahari on 26/03/2017.
 */
public class OrderEntity {
    @Id
    private  String orderId;
    private  String userId;
    private  Double quantityInKg;
    private  Double pricePerKg;
    private MarketSide side;

    public OrderEntity() {
    }

    public OrderEntity(String userId, Double quantityInKg, Double pricePerKg, MarketSide side) {
        this.userId = userId;
        this.quantityInKg = quantityInKg;
        this.pricePerKg = pricePerKg;
        this.side = side;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
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
}
