package com.zahari.liveorderboard.service;

import com.zahari.liveorderboard.domain.dto.MarketSide;
import com.zahari.liveorderboard.domain.dto.OrderDTO;
import com.zahari.liveorderboard.domain.entity.OrderEntity;
import com.zahari.liveorderboard.error.OrderNotFoundException;
import com.zahari.liveorderboard.mapper.DomainMapper;
import com.zahari.liveorderboard.repository.OrderEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static com.zahari.liveorderboard.mapper.DomainMapper.fromEntity;
import static com.zahari.liveorderboard.mapper.DomainMapper.toEntity;

/**
 * Created by zahari on 26/03/2017.
 */
@Service
public class OrderService implements IOrderService{

    private OrderEntityRepository repo;

    @Autowired
    public OrderService(OrderEntityRepository repo) {
        this.repo = repo;
    }

    @Override
    public OrderDTO createOrder(OrderDTO order) {
        OrderEntity created = this.repo.save(toEntity(order));
        return fromEntity(created);
    }

    @Override
    public void cancelOrder(String orderId) {
        if(!this.repo.exists(orderId)) {
            throw new OrderNotFoundException("Cannot cancel an order with id " + orderId + " as it does not exist");
        }
        this.repo.delete(orderId);
    }

    @Override
    public OrderDTO getOrder(String orderId) {
        return this.repo.findByOrderId(orderId).map(DomainMapper::fromEntity).orElseThrow(
                () -> new OrderNotFoundException("Cannot obtain an order with id " + orderId + " as it does not exist")
        );
    }

    @Override
    public Stream<OrderDTO> getAllOrders() {
        return this.repo.findAllOrders().map(DomainMapper::fromEntity);
    }

    @Override
    public Stream<OrderDTO> getBuyOrders() {
        return this.repo.findBySide(MarketSide.BUY).map(DomainMapper::fromEntity);
    }

    @Override
    public Stream<OrderDTO> getSellOrders() {
        return this.repo.findBySide(MarketSide.SELL).map(DomainMapper::fromEntity);
    }
}
