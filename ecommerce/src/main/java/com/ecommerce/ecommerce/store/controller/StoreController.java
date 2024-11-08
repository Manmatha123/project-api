package com.ecommerce.ecommerce.store.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.ecommerce.global.Status;
import com.ecommerce.ecommerce.store.entity.StoreCategoryFindDTO;
import com.ecommerce.ecommerce.store.entity.StoreDTO;
import com.ecommerce.ecommerce.store.service.StoreService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/store")
public class StoreController {
    

       
@Autowired
private StoreService storeService;

@GetMapping(value="/category/list")
public List<StoreCategoryFindDTO> onlyCategoryList(){
    return storeService.onlyCategoryList();
}

    @GetMapping(value="/list/place/{place}")
    public List<StoreDTO> findAllByPlace(@PathVariable String place){
        return storeService.findAllByPlace(place);
    }
    
    @GetMapping(value="/list/type/{type}")
    public List<StoreDTO> findAllByCategory(@PathVariable String category){
        return storeService.findAllByCategory(category);
    }
    @GetMapping(value="/id/{id}")
    public StoreDTO getById(@PathVariable Long id ){
        return storeService.getById(id);
    }

    @PostMapping(value="/saveorupdate")
    public Status saveOrUpdateStore(@RequestBody  StoreDTO storeDTO){
        return storeService.saveOrUpdate(storeDTO);
    }

    @GetMapping(value="/user/id/{id}")
    public StoreDTO getStoreByUserId(@PathVariable Long id ){
        return storeService.getStoreByUserId(id);
    }

    
    @GetMapping(value="/info")
    public StoreDTO getStoreInfo(){
        return storeService.getStoreInfo();
    }

}


