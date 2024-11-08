package com.ecommerce.ecommerce.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.cart.entity.Cart;
import com.ecommerce.ecommerce.cart.entity.CartDTO;
import com.ecommerce.ecommerce.cart.service.CartService;
import com.ecommerce.ecommerce.global.Status;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/cart")
public class CartController {
    
    @Autowired
    CartService cartService;

   @GetMapping(value="/list/user/id/{id}")
   public List<CartDTO> listByUserId(@PathVariable Long id){
    return cartService.listByUserId(id);
}

    @GetMapping(value="/id/{id}")
    public Cart getById(@PathVariable Long id ){
        return cartService.findByCartId(id);
    }
   
    // @PostMapping(value = "/saveorupdate")
    // public Status saveOrUpdate(@RequestBody CartSaveDTO cartDTO){
    //     return cartService.saveOrUpdate(cartDTO);
    // }

    @DeleteMapping(value="/delete/id/{id}")
    public Status deleteById(@PathVariable Long id ){
        return cartService.deleteCartById(id);
    }

    
}
