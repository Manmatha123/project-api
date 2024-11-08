package com.ecommerce.ecommerce.cart.entity;

import java.util.Date;
import java.util.List;

import com.ecommerce.ecommerce.cartProduct.entity.CartProductDTO;
import com.ecommerce.ecommerce.store.entity.StoreDTO;
import com.ecommerce.ecommerce.users.Entity.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {

    private Long id;

    private UserDTO user;
    private List<CartProductDTO> cartProducts;
    private Date updateon;
    private Float finalprice;
    private StoreDTO store;
    private boolean onlinedeliver;

    public CartDTO(Cart cart) {
        this.id = cart.getId();
        this.user = new UserDTO(cart.getUser());
        this.cartProducts = cart.getCartProducts().stream().map(CartProductDTO::new).toList();
        this.updateon = cart.getUpdateon();
        this.finalprice = cart.getFinalprice();
        this.store = new StoreDTO(cart.getStore());
        this.onlinedeliver=cart.isOnlinedeliver();
    }
}
