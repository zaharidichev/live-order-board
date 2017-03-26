package com.zahari.liveorderboard.repository;

import com.zahari.liveorderboard.domain.MarketSide;
import com.zahari.liveorderboard.domain.OrderDTO;
import com.zahari.liveorderboard.domain.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;

/**
 * Created by zahari on 26/03/2017.
 */
public interface OrderEntityRepository extends MongoRepository<OrderEntity,String> {
    Stream<OrderEntity> findBySide(MarketSide side);
}
