// package com.ecommerce.ecommerce.productStore.entity;

// import java.util.ArrayList;
// import java.util.List;

// import com.ecommerce.ecommerce.products.entity.Product;
// import com.ecommerce.ecommerce.products.entity.ProductDTO;
// import com.ecommerce.ecommerce.store.entity.Store;
// import com.ecommerce.ecommerce.store.entity.StoreDTO;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class ProductStoreDTO {
    

//     private Long id;

//     private StoreDTO store;

//    ProductDTO product;

//     public ProductStoreDTO(ProductStore productStore){
//         this.id=productStore.getId();
//         this.store=new StoreDTO(productStore.getStore());
//         this.product=new ProductDTO(productStore.getProduct());
//       }

// }
