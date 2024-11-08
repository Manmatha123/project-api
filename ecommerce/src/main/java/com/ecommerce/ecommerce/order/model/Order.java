package com.ecommerce.ecommerce.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecommerce.ecommerce.cart.entity.Cart;
import com.ecommerce.ecommerce.orderProduct.model.Orderproduct;
import com.ecommerce.ecommerce.store.entity.Store;
import com.ecommerce.ecommerce.users.Entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_store")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Orderproduct> orderProducts = new ArrayList<>();
    
    private Date orderdate;
    private Float finalprice;

    private String orderid;
    private boolean devivered;
    private boolean seen;
    private boolean onlinedeliver;

    public Order(OrderDto order) {
        this.id = order.getId();
        this.user = new User(order.getUser());
        this.store =new Store(order.getStore());
        this.finalprice = order.getFinalprice();
        this.orderdate = order.getOrderdate();
        this.orderProducts = order.getOrderProducts();
        this.orderid = order.getOrderid();
        this.devivered = order.isDevivered();
        this.seen = order.isSeen();
        this.onlinedeliver = order.isOnlinedeliver();
    }

    public Order(Order order) {
        this.user = order.getUser();
        this.store = order.getStore();
        this.finalprice = order.getFinalprice();
        this.orderdate = new Date();
        this.orderProducts = order.getOrderProducts().stream().map(Orderproduct::new).toList();
    }
}
