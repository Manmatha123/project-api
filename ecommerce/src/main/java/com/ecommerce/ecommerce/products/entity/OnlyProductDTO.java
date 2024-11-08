package com.ecommerce.ecommerce.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OnlyProductDTO {
    

    private Long id;
    private String image;
    private String name;
    private boolean available;
    private String price;
    private String discount;
    private String brand;
    private String category;
    private String descripton;
    private Float sellunit;
}
