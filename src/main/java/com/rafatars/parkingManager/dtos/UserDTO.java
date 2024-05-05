package com.rafatars.parkingManager.dtos;

import java.util.Set;

import com.rafatars.parkingManager.entities.CompanyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is created to represent the User entity.
 * So the controllers can use this class instead of the UserEntity class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    private Set<CompanyEntity> companies;

}
