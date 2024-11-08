package com.ecommerce.ecommerce.products.entity;

import com.ecommerce.ecommerce.store.entity.Store;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private String name;
    private boolean available;
    private String price;
    private String discount;
    private String brand;
    private String category;
    private String descripton;
    private Float quantity;
    private String sellunit;

    @ManyToOne
    @JsonBackReference
    private Store store;

    public Product(ProductDTO product) {
        this.id = product.getId();
        this.image = product.getImage();
        this.available = product.isAvailable();
        this.name = product.getName();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.brand = product.getBrand();
        this.category = product.getCategory();
        this.descripton = product.getDescripton();
        this.quantity = product.getQuantity();
        this.sellunit = product.getSellunit();
    }

}
