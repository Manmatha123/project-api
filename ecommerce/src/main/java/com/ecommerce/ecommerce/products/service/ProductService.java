package com.ecommerce.ecommerce.products.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ecommerce.ecommerce.global.Status;
import com.ecommerce.ecommerce.products.entity.Product;
import com.ecommerce.ecommerce.products.entity.ProductDTO;

public interface ProductService {
    
    
    // List<ProductDTO> listByUserId(Long id);

    List<ProductDTO> findAllByProductByName(String name);

    List<ProductDTO> findAllByCategory(String category);

    ProductDTO findByProductId(Long id);

    Status saveOrUpdate(ProductDTO productDTO,MultipartFile file);

    Status deleteProductById(Long id);

    List<Product> listByStoreId();

    Status issueBill(List<ProductDTO> productList);
}
