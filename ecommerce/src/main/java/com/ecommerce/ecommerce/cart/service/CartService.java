package com.ecommerce.ecommerce.cart.service;

import java.util.List;

import com.ecommerce.ecommerce.cart.entity.Cart;
import com.ecommerce.ecommerce.cart.entity.CartDTO;
import com.ecommerce.ecommerce.global.Status;

public interface CartService {

    List<CartDTO> listByUserId(Long id);

    Cart findByCartId(Long id);

    Status deleteCartById(Long id);


}
