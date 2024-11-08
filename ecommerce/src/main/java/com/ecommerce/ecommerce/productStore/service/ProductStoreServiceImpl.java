// package com.ecommerce.ecommerce.productStore.service;

// import java.io.IOException;
// import java.util.Base64;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;
// import org.springframework.util.StringUtils;
// import org.springframework.web.multipart.MultipartFile;

// import com.ecommerce.ecommerce.global.Status;
// import com.ecommerce.ecommerce.productStore.entity.ProductStore;
// import com.ecommerce.ecommerce.productStore.entity.ProductStoreDTO;
// import com.ecommerce.ecommerce.productStore.entity.ProductStoreUpd;
// import com.ecommerce.ecommerce.productStore.repository.ProductStoreRepo;
// import com.ecommerce.ecommerce.products.entity.Product;
// import com.ecommerce.ecommerce.store.entity.Store;
// import com.ecommerce.ecommerce.store.entity.StoreDTO;
// import com.ecommerce.ecommerce.users.Entity.User;
// import com.ecommerce.ecommerce.users.Repository.UserRepo;

// import jakarta.persistence.EntityNotFoundException;
// import lombok.extern.log4j.Log4j2;

// @Log4j2
// @Service
// public class ProductStoreServiceImpl implements ProductStoreService {

//     @Autowired
//     UserRepo userRepo;

//     @Autowired
//     ProductStoreRepo productStoreRepo;

//     @Override
//     public Status deleteById(Long id) {
//         productStoreRepo.findById(id).ifPresentOrElse(
//                 country -> {
//                     productStoreRepo.deleteById(id);
//                 },
//                 () -> {
//                     throw new EntityNotFoundException("product not found with ID: " + id);
//                 });
//         return new Status(true, "Deleted Successfully");
//     }

//     @Override
//     public List<ProductStoreDTO> findAll() {
//         return productStoreRepo.findAll().stream().map(ProductStoreDTO::new).toList();
//     }

//     @Override
//     public ProductStoreDTO getById(Long id) {
//         return productStoreRepo.findById(id).map(ProductStoreDTO::new)
//                 .orElseThrow(() -> new EntityNotFoundException("product not found with ID: " + id));
//     }

//     @Override
//     public ProductStoreDTO getByStoreId(Long id) {
//         return productStoreRepo.findByStore_id(id).map(ProductStoreDTO::new)
//                 .orElseThrow(() -> new EntityNotFoundException("product not found with ID: " + id));
//     }

//     @Override
//     public Status saveOrUpdate(ProductStoreUpd storeDTO, MultipartFile file) {
//         try {

//             Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//             User user = userRepo.findByPhone(authentication.getName());

//             ProductStore storeProduct = storeDTO.getId() == null ? newProductStore(storeDTO, file)
//                     : setStoreProduct(storeDTO, file);

//             productStoreRepo.save(storeProduct);

//             return new Status(true, storeDTO.getId() == null ? "Successfuly added" : "Update successfuly");
//         } catch (Exception e) {
//             log.error("Error saving or updating product", e.getMessage(), e);
//             return new Status(false, "An error occurred");
//         }
//     }

//     public ProductStore newProductStore(ProductStoreUpd pStoreUpd, MultipartFile file) throws IOException {

//         ProductStore pStore = new ProductStore();
//         Product product = new Product();
//         pStore.setStore(new Store(pStoreUpd.getStore()));
//         pStore.setId(pStoreUpd.getId());

//         product.setAvailable(pStoreUpd.getProduct().isAvailable());
//         product.setBrand(pStoreUpd.getProduct().getBrand());
//         product.setCategory(pStoreUpd.getProduct().getCategory());
//         product.setDescripton(pStoreUpd.getProduct().getDescripton());
//         product.setDiscount(pStoreUpd.getProduct().getDiscount());
//         product.setName(pStoreUpd.getProduct().getName());
//         product.setPrice(pStoreUpd.getProduct().getPrice());
//         product.setName(pStoreUpd.getProduct().getName());
//         String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//         if (fileName.contains("..")) {
//             return null;
//         }
//         product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

//         pStore.setProduct(product);

//         return pStore;
//     }

//     public ProductStore setStoreProduct(ProductStoreUpd pStoreUpd, MultipartFile file) throws IOException {
//         return productStoreRepo.findById(pStoreUpd.getId())
//                 .map(extProd -> {
//                     extProd.setStore(new Store(pStoreUpd.getStore()));
//                     extProd.getProduct().setAvailable(pStoreUpd.getProduct().isAvailable());
//                     extProd.getProduct().setBrand(pStoreUpd.getProduct().getBrand());
//                     extProd.getProduct().setCategory(pStoreUpd.getProduct().getCategory());
//                     extProd.getProduct().setDescripton(pStoreUpd.getProduct().getDescripton());
//                     extProd.getProduct().setDiscount(pStoreUpd.getProduct().getDiscount());
//                     extProd.getProduct().setName(pStoreUpd.getProduct().getName());
//                     extProd.getProduct().setPrice(pStoreUpd.getProduct().getPrice());

//                     if (!file.isEmpty()) {
//                         try {
//                             String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//                             if (fileName.contains("..")) {
//                                 return null;
//                             }
//                             extProd.getProduct().setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//                         } catch (IOException e) {
//                             e.printStackTrace();
//                         }
//                     }

//                     return extProd;
//                 }).orElseThrow(() -> new EntityNotFoundException("product not found with ID: " +
//                         pStoreUpd.getId()));
//     }
// }

// // @Autowired
// // StoreRepo storeRepo;

// // @Autowired
// // UserRepo userRepo;

// // @Autowired
// // AuthenticationManager authenticationManager;

// // @Override
// // public Status saveOrUpdate(StoreDTO storeDTO) {
// // try {

// // Authentication
// // authentication=SecurityContextHolder.getContext().getAuthentication();
// // User user=userRepo.findByPhone(authentication.getName());
// // Store store=storeDTO.getId()==null?new Store(storeDTO):setStore(storeDTO);
// // store.setUser(user);
// // storeRepo.save(store);

// // return new Status(true, storeDTO.getId() == null ? "Successfuly register" :
// // "Update successfuly");
// // } catch (Exception e) {
// // log.error("Error saving or updating store", e.getMessage(), e);
// // return new Status(false, "An error occurred");
// // }
// // }

// // private Store setStore(StoreDTO storeDTO) {
// // return storeRepo.findById(storeDTO.getId())
// // .map(existingUser -> {
// // existingUser.setName(storeDTO.getName());
// // existingUser.setCategory(storeDTO.getCategory());
// // existingUser.setClosetime(storeDTO.getClosetime());
// // existingUser.setDelivirycharge(storeDTO.getDelivirycharge());

// // return existingUser;
// // }).orElseThrow(() -> new EntityNotFoundException("store not found with ID: "
// // +
// // storeDTO.getId()));
// // }
