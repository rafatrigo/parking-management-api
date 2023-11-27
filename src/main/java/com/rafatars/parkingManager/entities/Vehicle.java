package com.rafatars.parkingManager.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is created to represent the Vehicle entity.
 * So the controllers can use this class instead of the VehicleEntity class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {
	private Long id;
	private String brand;
	private String model;
	private String color;
	private String plate;
	private VehicleType type;
	private CompanyEntity company;
}
