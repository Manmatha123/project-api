package com.ecommerce.ecommerce.users.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String phone;
    private String role;
    private String password;
    private String profileimage;
    private String address;

    public UserDTO(User user){
        this.id=user.getId();
        this.name=user.getName();
        this.phone=user.getPhone();
        this.profileimage=user.getProfileimage();
        this.password=user.getPassword();
        this.address=user.getAddress();
    }
}
