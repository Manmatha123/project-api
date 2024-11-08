package com.ecommerce.ecommerce.users.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFetchPojo {
    

    private Long id;
    private String name;
    private String phone;
    private String profileimage;
    private String address;

    public UserFetchPojo(User user){
        this.id=user.getId();
        this.name=user.getName();
        this.phone=user.getPhone();
        this.profileimage=user.getProfileimage();
        this.address=user.getAddress();
    }
}
