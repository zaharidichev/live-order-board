package com.zahari.liveorderboard.repository;

import com.zahari.liveorderboard.domain.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zahari on 26/03/2017.
 */
public interface OrderEntityRepository extends MongoRepository<OrderEntity,String> {
}
