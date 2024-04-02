package com.OrderService.OrderService.repository;

import com.OrderService.OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this interface serves as a repository and should be scanned during component scanning
public interface OrderRepository extends JpaRepository<Order, Long> {
    // JpaRepository provides basic CRUD operations for the specified entity type (Order)
    // Long is the type of the primary key of the Order entity

    // No need to define custom methods here since JpaRepository already provides methods for common operations
}
