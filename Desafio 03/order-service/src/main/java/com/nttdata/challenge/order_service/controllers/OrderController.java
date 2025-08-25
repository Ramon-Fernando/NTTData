package com.nttdata.challenge.order_service.controllers;

import com.nttdata.challenge.order_service.client.ProductServiceClient;
import com.nttdata.challenge.order_service.dto.OrderRequestDTO;
import com.nttdata.challenge.order_service.dto.OrderResponseDTO;
import com.nttdata.challenge.order_service.dto.ProductDTO;
import com.nttdata.challenge.order_service.dto.ProductPageDTO;
import com.nttdata.challenge.order_service.entities.Order;
import com.nttdata.challenge.order_service.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

        if (orders.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        ProductPageDTO productPage = productServiceClient.findAll();
        
        Map<Long, String> productNamesMap = productPage.getContent().stream()
                .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getNome()))
                .collect(Collectors.toMap(ProductDTO::getId, ProductDTO::getNome));

        List<OrderResponseDTO> dtos = orders.stream()
                .map(order -> mapToOrderResponseDTO(order, productNamesMap))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    private OrderResponseDTO mapToOrderResponseDTO(Order order, Map<Long, String> productNamesMap) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setMoment(order.getMoment());
        dto.setTotalPrice(order.getTotalPrice());

        List<String> productNames = order.getItems().stream()
                .map(item -> productNamesMap.getOrDefault(item.getProductId(), "Nome do produto não encontrado"))
                .collect(Collectors.toList());
        dto.setProductNames(productNames);

        return dto;
    }

    private OrderResponseDTO mapToOrderResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setMoment(order.getMoment());
        dto.setTotalPrice(order.getTotalPrice());

        try {
            List<String> productNames = order.getItems().stream()
                    .map(item -> {
                        ProductDTO product = productServiceClient.findById(item.getProductId());
                        return (product != null) ? product.getNome() : "Nome do produto não encontrado";
                    })
                    .collect(Collectors.toList());
            dto.setProductNames(productNames);
        } catch (Exception e) {
            dto.setProductNames(Collections.emptyList());
        }

        return dto;
    }
}
