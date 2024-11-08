package com.ecommerce.ecommerce.cartProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.cartProduct.entity.CartProduct;

public interface CartProductRepo extends JpaRepository<CartProduct,Long>{
    
}
