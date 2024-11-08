package com.ecommerce.ecommerce.cartProduct.service;

import com.ecommerce.ecommerce.cartProduct.entity.CartProductDTO;
import com.ecommerce.ecommerce.global.Status;

public interface CartProdectService {

    CartProductDTO findByCartProductId(Long id);

    Status saveOrUpdate(CartProductDTO cartProductDTO);

    Status deleteCartProductById(Long id);
    
}
