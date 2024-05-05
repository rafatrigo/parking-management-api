package com.rafatars.parkingManager.dtos;

import java.time.ZonedDateTime;

import com.rafatars.parkingManager.entities.VehicleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is created to represent the Store entity.
 * So the controllers can use this class instead of the StoreEntity class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {
    
    private Long id;
    private ZonedDateTime arrival;
    private ZonedDateTime departure;
    private Boolean open;

    private ParkingLotDTO parkingLot;
    private VehicleEntity vehicle;
}
