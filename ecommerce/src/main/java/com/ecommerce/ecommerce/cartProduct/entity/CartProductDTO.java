
package com.ecommerce.ecommerce.cartProduct.entity;


import java.util.Date;
import com.ecommerce.ecommerce.cart.entity.CartDTO;
import com.ecommerce.ecommerce.products.entity.ProductDTO;
import com.ecommerce.ecommerce.store.entity.StoreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartProductDTO {

    
    private Long id;
    private StoreDTO store;
    private CartDTO  cart;
    private ProductDTO product;
    private Float totalsingleproduct;
    private Float totalcost;
    private Date createdon;

    public CartProductDTO(CartProduct cartProduct){
        this.id=cartProduct.getId();
        this.product=new ProductDTO(cartProduct.getProduct());
        this.store=new StoreDTO(cartProduct.getStore());
        this.totalsingleproduct=cartProduct.getTotalsingleproduct();
        this.totalcost=cartProduct.getTotalcost();
        this.createdon=cartProduct.getCreatedon();
    }

}