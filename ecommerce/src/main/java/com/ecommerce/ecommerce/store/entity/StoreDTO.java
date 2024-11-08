package com.ecommerce.ecommerce.store.entity;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ecommerce.products.entity.ProductDTO;
import com.ecommerce.ecommerce.users.Entity.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDTO {
    

    private Long id;
    private String name;
    private String description;
    private String place;
    private String district;
    private String pin;
    private String category;
    private boolean delivory;
    private String delivirycharge;
    private String opentime;
    private String closetime;
    private UserDTO user;

private List<ProductDTO> product=new ArrayList<>();
    public StoreDTO(Store store){
        this.id=store.getId();
        this.name=store.getName();
        this.description=store.getDescription();
        this.place=store.getPlace();
        this.district=store.getDistrict();
        this.pin=store.getPin();
        this.category=store.getCategory();
        this.delivory=store.isDelivory();
        this.delivirycharge=store.getDelivirycharge();
        this.opentime=store.getOpentime();
        this.closetime=store.getClosetime();
        this.user=new UserDTO(store.getUser());
        this.product=store.getProduct().stream().map(ProductDTO::new).toList();
    }
}
