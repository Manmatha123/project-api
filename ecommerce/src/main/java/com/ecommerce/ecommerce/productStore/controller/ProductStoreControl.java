// package com.ecommerce.ecommerce.productStore.controller;


// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.ecommerce.ecommerce.global.LoginDTO;
// import com.ecommerce.ecommerce.global.Status;
// import com.ecommerce.ecommerce.productStore.entity.ProductStoreDTO;
// import com.ecommerce.ecommerce.productStore.entity.ProductStoreUpd;
// import com.ecommerce.ecommerce.productStore.service.ProductStoreService;
// import com.ecommerce.ecommerce.users.Entity.UserDTO;
// import com.ecommerce.ecommerce.users.Entity.UserFetchPojo;
// import com.ecommerce.ecommerce.users.service.UserService;

// @CrossOrigin(origins = "*")
// @RestController
// @RequestMapping("/v1/api/storeproduct")
// public class ProductStoreControl {

//     @Autowired
//     private ProductStoreService productStoreService;

//     @GetMapping(value = "/list")
//     public List<ProductStoreDTO> findAll() {
//         return productStoreService.findAll();
//     }

//     @GetMapping(value = "/id/{id}")
//     public ProductStoreDTO getById(@PathVariable Long id) {
//         return productStoreService.getById(id);
//     }

//     @GetMapping(value = "/product/id/{id2}")
//     public ProductStoreDTO getByUserAndStoreId(@PathVariable Long id1) {
//         return productStoreService.getByStoreId(id1);
//     }
//     @RequestMapping(value = "/saveorupdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//     public Status saveproductStore( @RequestPart("productstore") ProductStoreUpd productStoreUpd,@RequestPart("file")MultipartFile file) {
//         return productStoreService.saveOrUpdate(productStoreUpd,file);
//     }

//     @DeleteMapping(value = "/delete/id/{id}")
//     public Status deleteById(@PathVariable Long id) {
//         return productStoreService.deleteById(id);
//     }

// }
