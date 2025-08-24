package com.nttdata.challenge.order_service.controllers;

import com.nttdata.challenge.order_service.client.ProductServiceClient;
import com.nttdata.challenge.order_service.dto.OrderRequestDTO;
import com.nttdata.challenge.order_service.dto.OrderResponseDTO;
import com.nttdata.challenge.order_service.entities.Order;
import com.nttdata.challenge.order_service.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    private final OrderService orderService;
    private final ProductServiceClient productServiceClient;

    public OrderController(OrderService orderService, ProductServiceClient productServiceClient) {
        this.orderService = orderService;
        this.productServiceClient = productServiceClient;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        try {
            Order createdOrder = orderService.createOrder(orderRequestDTO);
            OrderResponseDTO response = mapToOrderResponseDTO(createdOrder);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<Order> orders = orderService.findAll();
        List<OrderResponseDTO> dtos = orders.stream()
                .map(this::mapToOrderResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Método auxiliar para mapear a entidade Order para um DTO de resposta
    private OrderResponseDTO mapToOrderResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setMoment(order.getMoment());
        dto.setTotalPrice(order.getTotalPrice());

        // Busca os nomes dos produtos para uma resposta mais amigável
        List<String> productNames = order.getItems().stream()
                .map(item -> productServiceClient.findById(item.getProductId()).getNome())
                .collect(Collectors.toList());
        dto.setProductNames(productNames);

        return dto;
    }
}