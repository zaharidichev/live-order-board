package com.zahari.liveorderboard.repository;

import com.zahari.liveorderboard.domain.dto.MarketSide;
import com.zahari.liveorderboard.domain.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by zahari on 26/03/2017.
 */
public interface OrderEntityRepository extends MongoRepository<OrderEntity,String> {

    @Query("{}")
    Stream<OrderEntity> findAllOrders();
    Optional<OrderEntity> findByOrderId(String orderId);
    Stream<OrderEntity> findBySide(MarketSide side);
}
