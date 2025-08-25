package com.nttdata.challenge.order_service.repositories;

import com.nttdata.challenge.order_service.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT obj FROM Order obj JOIN FETCH obj.items")
    List<Order> findAllWithItems();


}
