package com.ecommerce.ecommerce.cartProduct.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerce.cart.entity.Cart;
import com.ecommerce.ecommerce.cart.repository.CartRepo;
import com.ecommerce.ecommerce.cartProduct.entity.CartProduct;
import com.ecommerce.ecommerce.cartProduct.entity.CartProductDTO;
import com.ecommerce.ecommerce.cartProduct.repository.CartProductRepo;
import com.ecommerce.ecommerce.global.Status;
import com.ecommerce.ecommerce.products.entity.Product;
import com.ecommerce.ecommerce.store.entity.Store;
import com.ecommerce.ecommerce.users.Entity.User;
import com.ecommerce.ecommerce.users.Repository.UserRepo;
import com.ecommerce.ecommerce.users.service.UserServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CartProdectServiceImpl implements CartProdectService{
 
 @Autowired
 CartProductRepo cProductRepo;

 @Autowired
 UserServiceImpl userServiceImpl;

 @Autowired
UserRepo userRepo;

 @Autowired
 CartRepo cartRepo;

 CartProduct delCartProduct;

private Float priceOfAllObj=(float)0;

 @Override
    public Status deleteCartProductById(Long id) {
        cProductRepo.findById(id).ifPresentOrElse(
                country -> {
                    cProductRepo.deleteById(id);
                },
                () -> {
                    throw new EntityNotFoundException("cart product not found with ID: " + id);
                });
        return new Status(true, "Deleted Successfully");
    }

    @Override
    public CartProductDTO findByCartProductId(Long id) {
   return  cProductRepo.findById(id).map(CartProductDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Cart Product  not found with ID: " + id));
    }

    @Override
    public Status saveOrUpdate(CartProductDTO cartProductDTO) {

      try{
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

User user=userRepo.findByPhone(authentication.getName());


if(cartProductDTO.getId()!=null){
   this.delCartProduct=cProductRepo.findById(cartProductDTO.getId()).get();
}

CartProduct cartProduct=cartProductDTO.getId()==null?new CartProduct(cartProductDTO):setCartProduct(cartProductDTO);

cProductRepo.save(cartProduct);

boolean isStoresProductavl=cartRepo.existsByUser_idAndStore_id(user.getId(),cartProductDTO.getStore().getId());


//<if product present in cart>

if(isStoresProductavl){ 
Cart cart=cartRepo.findByUser_idAndStore_id(user.getId(),cartProductDTO.getStore().getId());

cart.getCartProducts().forEach((obj)->{
  this.priceOfAllObj+=obj.getTotalcost();
});
this.priceOfAllObj+=cartProductDTO.getTotalcost();
cart.setFinalprice(this.priceOfAllObj);
cart.getCartProducts().add(cartProduct);

if(cartProductDTO.getId()!=null){
  cart.getCartProducts().remove(this.delCartProduct);
}

cartRepo.save(cart);
 return new Status(true,"successfuly added");
}
//</if product present in cart>

//<if product not present in cart>
Cart cartObj=new Cart();
cartObj.setStore(new Store(cartProductDTO.getStore()));
cartObj.setUser(user);
cartObj.setUpdateon(new Date());
cartObj.getCartProducts().add(cartProduct);
cartObj.setFinalprice(cartProductDTO.getTotalcost());
cartRepo.save(cartObj);
//</if product not present in cart>

        return new Status(true, cartProductDTO.getId() == null ?  "successfuly added" :  "update successfuly");
      }
      catch (Exception e) {
        log.error("error while adding cart product");
        return new Status(false, "An error occurred");
      }
    }

   //<update cart product>
        public CartProduct setCartProduct(CartProductDTO cProductDTO){
        return cProductRepo.findById(cProductDTO.getId())
        .map(existObj -> {
          existObj.setCreatedon(cProductDTO.getCreatedon());
          existObj.setProduct(new Product(cProductDTO.getProduct()));
          existObj.setStore(new Store(cProductDTO.getStore()));
          existObj.setTotalcost(cProductDTO.getTotalcost());
          existObj.setTotalsingleproduct(cProductDTO.getTotalsingleproduct());

        return existObj;
        }).orElseThrow(() -> new EntityNotFoundException("Cart product not found with ID: " +
        cProductDTO.getId())); 
    }
       //</update cart product>
}
