package com.ecommerce.ecommerce.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.store.entity.Store;

public interface StoreRepo extends JpaRepository<Store,Long>{

    Optional<Store> findByUser_id(Long id);

    Optional<Store> findAllByPlaceContainingIgnoreCase(String place);

    Optional<Store> findAllByCategoryContainingIgnoreCase(String type);

    Store findByUser_phone(String name);
    
}
