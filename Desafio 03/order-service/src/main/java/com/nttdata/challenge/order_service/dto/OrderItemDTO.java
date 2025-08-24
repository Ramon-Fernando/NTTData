package com.nttdata.challenge.order_service.dto;

public class OrderItemDTO {
    private Long productId;
    private Integer quantity;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
