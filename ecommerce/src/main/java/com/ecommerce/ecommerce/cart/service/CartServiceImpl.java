package com.ecommerce.ecommerce.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.cart.entity.Cart;
import com.ecommerce.ecommerce.cart.entity.CartDTO;
import com.ecommerce.ecommerce.cart.repository.CartRepo;
import com.ecommerce.ecommerce.cartProduct.service.CartProdectServiceImpl;
import com.ecommerce.ecommerce.global.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartProdectServiceImpl cartProdectServiceImpl;

    @Override
    public Status deleteCartById(Long id) {
        cartRepo.findById(id).ifPresentOrElse(
                country -> {
                    
                   Cart cart= cartRepo.findById(id).get();
                   cart.getCartProducts().forEach((obj)->{
                    cartProdectServiceImpl.deleteCartProductById(obj.getId());
                   });

                   cartRepo.deleteById(id);

                },
                () -> {
                    throw new EntityNotFoundException("Cart not found with ID: " + id);
                });
        return new Status(true, "Deleted Successfully");
    }

    @Override
    public Cart findByCartId(Long id) {
        return cartRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart found with ID: " + id));
    }

    @Override
    public List<CartDTO> listByUserId(Long id) {
        return cartRepo.findAllByUser_id(id).stream().map(CartDTO::new).toList();
    }

    // @Override
    // public Status saveOrUpdate(CartSaveDTO cartDTO) {
    // boolean isExists = cartRepo.existsByUser_idAndStore_idAndIdNot(
    // cartDTO.getUser().getId(),cartDTO.getStore().getId(),
    // cartDTO.getId() != null ? cartDTO.getId() : -1);

    // if(isExists){
    // Cart cart=cartRepo.findById(cartDTO.getId()).get();
    // cart.getProduct().add(new CartProduct(cartDTO.getProduct()));
    // cart.setTotalcost(cartDTO.getTotalcost());
    // cartRepo.save(cart);
    // return new Status(true, "Cart product successfuly");
    // }

    // Cart cartdata=new Cart(cartDTO);
    // cartdata.getProduct().add(new CartProduct(cartDTO.getProduct()));
    // cartdata.setStore(new Store(cartDTO.getStore()));
    // cartdata.setUser(new User(cartDTO.getUser()));
    // cartDTO.setTotalcost(cartDTO.getTotalcost());
    // cartRepo.save(cartdata);

    // return null;
    // }

}
