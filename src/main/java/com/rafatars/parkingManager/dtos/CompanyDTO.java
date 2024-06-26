package com.rafatars.parkingManager.dtos;

import java.util.Set;

import com.rafatars.parkingManager.entities.ParkingLotEntity;
import com.rafatars.parkingManager.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is created to represent the Company entity.
 * So the controllers can use this class instead of the CompanyEntity class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {
	private Long id;
	private String name;
	private String cnpj;
	private String address;
	private String phone;

	private Set<ParkingLotEntity> parkingLots;
	private Set<UserEntity> users;
}
