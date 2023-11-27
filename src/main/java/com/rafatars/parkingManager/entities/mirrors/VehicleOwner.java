package com.rafatars.parkingManager.entities.mirrors;

import java.util.Set;

import com.rafatars.parkingManager.entities.VehicleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is created to represent the VehicleOwner entity.
 * So the controllers can use this class instead of the VehicleOwnerEntity class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleOwner {
    
    private Long id;
    private String name;
    private String phone;
    private String email;

    private Set<VehicleEntity> vehicles;

}
