package com.zahari.liveorderboard.service;

import com.zahari.liveorderboard.domain.dto.OrderDTO;
import com.zahari.liveorderboard.error.exception.ValidationException;

/**
 *
 * Used to validate the input of the user when placing an order
 *
 * Created by zahari on 26/03/2017.
 */
public class OrderDTOPostValidator {
    public static void validate(OrderDTO dto) {
        if(dto.getPricePerKg() < 0) {
            throw new ValidationException("Cannot create an order with negative price");
        }

        if(dto.getQuantityInKg() < 0.001) {
            throw new ValidationException("Cannot create an order with size less than 0.001");
        }

        if(dto.getSide() == null) {
            throw new ValidationException("Cannot create an order with missing market side");
        }
        if(dto.getUserId() == null || dto.getUserId().isEmpty()) {
            throw new ValidationException("Cannot create an order with missing or empty userId");
        }

        if(dto.getOrderId() != null ) {
            throw new ValidationException("Cannot create an order with ID. Ids are assigned automatically by the system");
        }
    }
}
