

package com.ecommerce.ecommerce.cartProduct.entity;

import java.util.Date;

import com.ecommerce.ecommerce.products.entity.Product;
import com.ecommerce.ecommerce.store.entity.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CartProduct {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Float totalsingleproduct;
    private Float totalcost;
    private Date createdon;

    public CartProduct(CartProductDTO cartProduct){
        this.id=cartProduct.getId();
        this.product=new Product(cartProduct.getProduct());
        this.store=new Store(cartProduct.getStore());
        this.totalsingleproduct=cartProduct.getTotalsingleproduct();
        this.totalcost=cartProduct.getTotalcost();
        this.createdon=cartProduct.getCreatedon();
    }
}