package com.ecommerce.ecommerce.store.service;

import java.util.List;

import com.ecommerce.ecommerce.global.Status;
import com.ecommerce.ecommerce.store.entity.StoreCategoryFindDTO;
import com.ecommerce.ecommerce.store.entity.StoreDTO;

public interface StoreService {



    Status saveOrUpdate(StoreDTO storeDTO);

    StoreDTO getById(Long id);

    List<StoreDTO> findAllByPlace(String place);

    StoreDTO getStoreByUserId(Long id);

    List<StoreDTO> findAllByCategory(String type);

    List<StoreCategoryFindDTO> onlyCategoryList();

    StoreDTO getStoreInfo();
    
}
