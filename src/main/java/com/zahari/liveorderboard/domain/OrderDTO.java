package com.zahari.liveorderboard.domain;

/**
 * Created by zahari on 26/03/2017.
 */
public class OrderDTO {

    private  String orderId;
    private  String userId;
    private  Double quantityInKg;
    private  Double pricePerKg;
    private MarketSide side;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String userId, Double quantityInKg, Double pricePerKg, MarketSide side) {
        this.orderId = orderId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDTO orderDTO = (OrderDTO) o;

        if (orderId != null ? !orderId.equals(orderDTO.orderId) : orderDTO.orderId != null) return false;
        if (userId != null ? !userId.equals(orderDTO.userId) : orderDTO.userId != null) return false;
        if (quantityInKg != null ? !quantityInKg.equals(orderDTO.quantityInKg) : orderDTO.quantityInKg != null)
            return false;
        if (pricePerKg != null ? !pricePerKg.equals(orderDTO.pricePerKg) : orderDTO.pricePerKg != null) return false;
        return side == orderDTO.side;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (quantityInKg != null ? quantityInKg.hashCode() : 0);
        result = 31 * result + (pricePerKg != null ? pricePerKg.hashCode() : 0);
        result = 31 * result + (side != null ? side.hashCode() : 0);
        return result;
    }
}
