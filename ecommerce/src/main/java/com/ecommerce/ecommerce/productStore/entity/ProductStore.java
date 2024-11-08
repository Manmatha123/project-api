// package com.ecommerce.ecommerce.productStore.entity;

// import java.util.ArrayList;
// import java.util.List;

// import com.ecommerce.ecommerce.products.entity.Product;
// import com.ecommerce.ecommerce.products.entity.ProductDTO;
// import com.ecommerce.ecommerce.store.entity.Store;
// import com.ecommerce.ecommerce.store.entity.StoreDTO;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;

// @AllArgsConstructor
// @NoArgsConstructor
// @Data
// public class ProductStore {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;
     
//     @OneToOne
//     @JoinColumn(name = "store_id")
//     private Store store;

//     private Product product;

//         public ProductStore(ProductStoreDTO productStore){
//         this.id=productStore.getId();
//         this.store=new Store(productStore.getStore());
//         this.product=new Product(productStore.getProduct());
//       }

// }
