package com.ecommerce.ecommerce.products.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.ecommerce.products.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{

    List<Product> findAllByCategory(String category);
    List<Product> findAllByStore_idAndNameContainingIgnoreCase(Long id,String name);

    // List<Product> findAllByStore_place(String place);

    // List<Product> findAllByStore_user_id(Long id);

    
}
