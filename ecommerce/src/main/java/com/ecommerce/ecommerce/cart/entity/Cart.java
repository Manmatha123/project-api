package com.ecommerce.ecommerce.cart.entity;

import java.util.Date;
import java.util.List;

import com.ecommerce.ecommerce.cartProduct.entity.CartProduct;
import com.ecommerce.ecommerce.store.entity.Store;
import com.ecommerce.ecommerce.users.Entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart")
    private List<CartProduct> cartProducts;

    private Date updateon;
    private Float finalprice;
    private boolean onlinedeliver;

    public Cart(CartDTO cart) {
        this.id = cart.getId();
        this.user = new User(cart.getUser());
        this.cartProducts = cart.getCartProducts().stream().map(CartProduct::new).toList();
        this.updateon = cart.getUpdateon();
        this.finalprice = cart.getFinalprice();
        this.store = new Store(cart.getStore());
        this.onlinedeliver=cart.isOnlinedeliver();
    }

}
