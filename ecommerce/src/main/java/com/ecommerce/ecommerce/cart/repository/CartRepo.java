package com.ecommerce.ecommerce.cart.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.cart.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long>{

    List<Cart> findAllByUser_id(Long id);

    boolean existsByUser_idAndStore_idAndIdNot(Long id, Long id2, long l);

    boolean existsByUser_idAndStore_id(Long id, Long id2);

    Cart findByUser_idAndStore_id(Long id, Long id2);
    
}
