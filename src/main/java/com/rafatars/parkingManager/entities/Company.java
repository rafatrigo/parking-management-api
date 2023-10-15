package com.rafatars.parkingManager.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
	private Long id;
	private String name;
	private String cnpj;
	private String address;
	private String phone;
	private int motorcycleParkingSpaces;
	private int carParkingSpaces;
}
