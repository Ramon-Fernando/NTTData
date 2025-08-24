package com.nttdata.challenge.order_service.dto;

import java.time.Instant;
import java.util.List;

public class OrderResponseDTO {

    private Long orderId;
    private Instant moment;
    private List<String> productNames;
    private Double totalPrice;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Long orderId, Instant moment, Double totalPrice) {
        this.orderId = orderId;
        this.moment = moment;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public List<String> getProductNames() {
        return productNames;
    }

    public void setProductNames(List<String> productNames) {
        this.productNames = productNames;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
