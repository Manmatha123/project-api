package com.ecommerce.ecommerce.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreCategoryFindDTO {
    private String category;
private Long id;
    public StoreCategoryFindDTO(Store store){
        this.category=store.getCategory();
        this.id=store.getId();
    }
}
