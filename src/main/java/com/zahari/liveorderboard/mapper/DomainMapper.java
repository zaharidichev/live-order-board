package com.zahari.liveorderboard.mapper;

import com.zahari.liveorderboard.domain.dto.OrderDTO;
import com.zahari.liveorderboard.domain.entity.OrderEntity;

/**
 *
 * Maps an entity to a dto and back
 *
 * Created by zahari on 26/03/2017.
 */
public class DomainMapper {

    public static OrderEntity toEntity(OrderDTO dto) {
        return new OrderEntity(dto.getUserId(),dto.getQuantityInKg(),dto.getPricePerKg(),dto.getSide());
    }

    public static OrderDTO fromEntity(OrderEntity ent) {
        return new OrderDTO(ent.getOrderId(),ent.getUserId(),ent.getQuantityInKg(),ent.getPricePerKg(),ent.getSide());
    }


}
