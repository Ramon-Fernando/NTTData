package com.nttdata.challenge.order_service.dto;

import java.util.List;

public class OrderRequestDTO {

    private List<OrderItemDTO> items;

    public OrderRequestDTO() {
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
