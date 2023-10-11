package com.rafatars.parkingManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="companies")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String id;
	private String name;
	private String cnpj;
	private String address;
	private String phone;
	private int motorcycleParkingSpaces;
	private int carParkingSpaces;
	
}
