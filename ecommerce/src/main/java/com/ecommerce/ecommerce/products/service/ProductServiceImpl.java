package com.ecommerce.ecommerce.products.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerce.global.Status;
import com.ecommerce.ecommerce.products.entity.Product;
import com.ecommerce.ecommerce.products.entity.ProductDTO;
import com.ecommerce.ecommerce.products.repository.ProductRepo;
import com.ecommerce.ecommerce.store.entity.Store;
import com.ecommerce.ecommerce.store.repository.StoreRepo;
import com.ecommerce.ecommerce.users.Entity.User;
import com.ecommerce.ecommerce.users.Repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StoreRepo storeRepo;

    @Override
    public Status deleteProductById(Long id) {
        productRepo.findById(id).ifPresentOrElse(
                country -> {
                    productRepo.deleteById(id);
                },
                () -> {
                    throw new EntityNotFoundException("product not found with ID: " + id);
                });
        return new Status(true, "Deleted Successfully");
    }

    @Override
    public List<ProductDTO> findAllByCategory(String category) {
        return productRepo.findAllByCategory(category).stream().map(ProductDTO::new).toList();
    }

    // @Override
    // public List<ProductDTO> findAllByStorePlace(String place) {
    // return
    // productRepo.findAllByStore_place(place).stream().map(ProductDTO::new).toList();
    // }

    @Override
    public ProductDTO findByProductId(Long id) {
        return productRepo.findById(id).map(ProductDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
    }

    @Override
    public List<ProductDTO> findAllByProductByName(String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Store store=storeRepo.findByUser_phone(authentication.getName());
    return productRepo.findAllByStore_idAndNameContainingIgnoreCase(store.getId(),name).stream().map(ProductDTO::new).toList();
    }

    @Override
    public Status saveOrUpdate(ProductDTO productDTO, MultipartFile file) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findByPhone(authentication.getName());
            Store store = storeRepo.findByUser_id(user.getId()).get();

            Product product = new Product();
            if (productDTO.getId() == null) {
                product.setBrand(productDTO.getBrand());
                product.setAvailable(productDTO.isAvailable());
                product.setCategory(productDTO.getCategory());
                product.setDescripton(productDTO.getDescripton());
                product.setDiscount(productDTO.getDiscount());
                product.setName(productDTO.getName());
                product.setId(productDTO.getId());
                product.setPrice(productDTO.getPrice());
                product.setQuantity(productDTO.getQuantity());
                product.setSellunit(productDTO.getSellunit());
                product.setStore(store);
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                if (!fileName.contains("..")) {

                    product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
                }

            } else {
                product = setProduct(productDTO, file);

                Product delProduct = productRepo.findById(productDTO.getId()).get();
                store.getProduct().remove(delProduct);
            }

            productRepo.save(product);
            store.getProduct().add(product);
            storeRepo.save(store);

            return new Status(true, productDTO.getId() == null ? "successfuly added" : "update successfuly");
        } catch (Exception e) {
            log.error("Error saving or updating product: {}", e.getMessage(), e);
            return new Status(false, "An error occurred");
        }
    }

    public Product setProduct(ProductDTO productDTO, MultipartFile file) {
        return productRepo.findById(productDTO.getId())
                .map(product -> {
                    product.setBrand(productDTO.getBrand());
                    product.setAvailable(productDTO.isAvailable());
                    product.setCategory(productDTO.getCategory());
                    product.setDescripton(productDTO.getDescripton());
                    product.setDiscount(productDTO.getDiscount());
                    product.setName(productDTO.getName());
                    product.setPrice(productDTO.getPrice());
                    product.setQuantity(productDTO.getQuantity());
                    product.setSellunit(productDTO.getSellunit());
                    try {
                        if(file!=null && !file.isEmpty()){
                            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                            if (!fileName.contains("..")) {
                                product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
                            }
                        }
                    } catch (IOException e) { }

                    return product;
                }).orElseThrow(() -> new EntityNotFoundException("product not found with ID: " +
                        productDTO.getId()));
    }

    @Override
    public List<Product> listByStoreId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Store store=storeRepo.findByUser_phone(authentication.getName());
        List<Product>  productlist=store.getProduct();
        return productlist;
    }

    @Override
    public Status issueBill(List<ProductDTO> productList) {
try {
    productList.stream().forEach(item->{
        Product product=productRepo.findById(item.getId()).orElseThrow(()->
             new EntityNotFoundException("product not found with ID: "+item.getId())
        );
        product.setQuantity(product.getQuantity()-item.getQuantity());
        productRepo.save(product);
    });
return new Status(true,"Product placed");
} catch (Exception e) {
    return new Status(true,"Fail to placed product");
}

    }



    
}
