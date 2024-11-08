package com.ecommerce.ecommerce.store.entity;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ecommerce.products.entity.Product;
import com.ecommerce.ecommerce.users.Entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

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


    // @JsonManagedReference
    @JoinColumn(name = "store")
    @OneToMany
    private List<Product> product=new ArrayList<>();

    public Store(StoreDTO store){
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
        this.user=new User(store.getUser());
        this.product=store.getProduct().stream().map(Product::new).toList();
    }
}
