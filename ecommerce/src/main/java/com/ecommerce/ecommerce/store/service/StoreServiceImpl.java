package com.ecommerce.ecommerce.store.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerce.global.Status;
import com.ecommerce.ecommerce.store.entity.Store;
import com.ecommerce.ecommerce.store.entity.StoreCategoryFindDTO;
import com.ecommerce.ecommerce.store.entity.StoreDTO;
import com.ecommerce.ecommerce.store.repository.StoreRepo;
import com.ecommerce.ecommerce.users.Entity.User;
import com.ecommerce.ecommerce.users.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    StoreRepo storeRepo;

    @Autowired 
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public StoreDTO getById(Long id) {
        return storeRepo.findById(id).map(StoreDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + id));
    }

    @Override
    public StoreDTO getStoreByUserId(Long id) {
            return storeRepo.findByUser_id(id).map(StoreDTO::new)
                    .orElseThrow(() -> new EntityNotFoundException("Store not found with user ID: " + id));

    }

    @Override
    public StoreDTO getStoreInfo() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByPhone(authentication.getName());
        Long id=user.getId();
        return storeRepo.findByUser_id(id).map(StoreDTO::new) .orElseThrow(() -> new EntityNotFoundException("Store not found with user ID: " + id));
    }

    @Override
    public Status saveOrUpdate(StoreDTO storeDTO) {
        try {

            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            User user=userRepo.findByPhone(authentication.getName());
          Store store=storeDTO.getId()==null?new Store(storeDTO):setStore(storeDTO);
            store.setUser(user);
            storeRepo.save(store);

            return new Status(true, storeDTO.getId() == null ?  "Successfuly register" : "Update successfuly");
        } catch (Exception e) {
            log.error("Error saving or updating store", e.getMessage(), e);
            return new Status(false, "An error occurred");
        }
    }
   

    private Store setStore(StoreDTO storeDTO) {
    return storeRepo.findById(storeDTO.getId())
    .map(existingUser -> {
        existingUser.setName(storeDTO.getName());
        existingUser.setCategory(storeDTO.getCategory());
        existingUser.setClosetime(storeDTO.getClosetime());
        existingUser.setDelivirycharge(storeDTO.getDelivirycharge());
        existingUser.setDelivory(storeDTO.isDelivory());
        existingUser.setDescription(storeDTO.getDescription());
        existingUser.setDistrict(storeDTO.getDistrict());
        existingUser.setOpentime(storeDTO.getOpentime());
        existingUser.setPin(storeDTO.getPin());
        existingUser.setPlace(storeDTO.getPlace());
        
    return existingUser;
    }).orElseThrow(() -> new EntityNotFoundException("store not found with ID: " +
    storeDTO.getId()));
    }

    @Override
    public List<StoreDTO> findAllByPlace(String place) {
        return storeRepo.findAllByPlaceContainingIgnoreCase(place).stream().map(StoreDTO::new).toList();
    }



    @Override
    public List<StoreDTO> findAllByCategory(String type) {
        return storeRepo.findAllByCategoryContainingIgnoreCase(type).stream().map(StoreDTO::new).toList();
    }



    @Override
    public List<StoreCategoryFindDTO> onlyCategoryList() {
        return storeRepo.findAll().stream().map(StoreCategoryFindDTO::new).toList();
    }






    
}
