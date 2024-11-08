package com.ecommerce.ecommerce.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecommerce.ecommerce.cart.entity.Cart;
import com.ecommerce.ecommerce.orderProduct.model.Orderproduct;
import com.ecommerce.ecommerce.store.entity.Store;
import com.ecommerce.ecommerce.store.entity.StoreDTO;
import com.ecommerce.ecommerce.users.Entity.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    
    private Long id;
    private UserDTO user;
    private StoreDTO store;
    private List<Orderproduct> orderProducts = new ArrayList<>();
    
    private Date orderdate;
    private Float finalprice;

    private String orderid;
    private boolean devivered;
    private boolean seen;
    private boolean onlinedeliver;

    public OrderDto(Order order){
        this.id=order.getId();
        this.user=new UserDTO(order.getUser());
        this.store=new StoreDTO(order.getStore());
        this.finalprice=order.getFinalprice();
        this.orderdate=order.getOrderdate();
        this.orderProducts=order.getOrderProducts();
        this.orderid=order.getOrderid();
        this.devivered=order.isDevivered();
        this.seen=order.isSeen();
        this.onlinedeliver=order.isOnlinedeliver();
    }

    //     public OrderDto(Order order){
    //     this.user=new UserDTO(order.getUser());
    //     this.store=order.getStore();
    //     this.finalprice=order.getFinalprice();
    //     this.orderdate=new Date();
    //     this.orderProducts=order.getOrderProducts().stream().map(Orderproduct::new).toList();
    // }
}
