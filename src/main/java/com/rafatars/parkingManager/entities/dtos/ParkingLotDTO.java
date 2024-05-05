package com.rafatars.parkingManager.entities.dtos;

import com.rafatars.parkingManager.entities.CompanyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is created to represent the ParkingLot entity.
 * So the controllers can use this class instead of the ParkingLotEntity class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLotDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private int carSpaces;
    private int motorcycleSpaces;
    private CompanyEntity company;
}
