package com.nttdata.challenge.order_service.entities;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemId implements Serializable {

    private Long order;
    private Long productId;

    public OrderItemId() {
    }

    public OrderItemId(Long order, Long productId) {
        this.order = order;
        this.productId = productId;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderItemId)) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(order, that.order) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, productId);
    }
}
