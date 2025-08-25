package com.nttdata.challenge.order_service.services;

import com.nttdata.challenge.order_service.client.ProductServiceClient;
import com.nttdata.challenge.order_service.dto.OrderRequestDTO;
import com.nttdata.challenge.order_service.dto.ProductDTO;
import com.nttdata.challenge.order_service.entities.Order;
import com.nttdata.challenge.order_service.entities.OrderItem;
import com.nttdata.challenge.order_service.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ProductServiceClient client;

    public OrderService(OrderRepository repository, ProductServiceClient client) {
        this.repository = repository;
        this.client = client;
    }

    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setMoment(Instant.now());

        Set<OrderItem> items = orderRequestDTO.getItems().stream().map(orderItemDTO -> {
            ProductDTO product = client.findById(orderItemDTO.getProductId());
            if (product == null) {
                throw new IllegalArgumentException("Produto não encontrado: " + orderItemDTO.getProductId());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setPrice(product.getPreco());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toSet());

        order.setItems(items);

        double totalPrice = items.stream()
                .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .sum();
        order.setTotalPrice(totalPrice);

        return repository.save(order);
    }

    public List<Order> findAll() {
        return repository.findAllWithItems();
    }
}
