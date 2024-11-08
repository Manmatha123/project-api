package com.ecommerce.ecommerce.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private Long id;
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

    public ProductDTO(Product product) {
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
