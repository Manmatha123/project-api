package com.ecommerce.ecommerce.orderProduct.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.order.model.Order;

public interface OrderProductRepo extends JpaRepository<Orderproduct,Long>{
    
}
