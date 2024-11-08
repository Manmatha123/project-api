package com.ecommerce.ecommerce.users.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
   

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
    private String role ="ADMIN";
    private String password;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String profileimage;
    private String address;



    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = "ADMIN";
        }
    }
    public User(UserDTO user){
        this.name=user.getName();
        this.phone=user.getPhone();
        this.profileimage=user.getProfileimage();
        this.address=user.getAddress();
    }

}
