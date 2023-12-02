package com.rafatars.parkingManager.entities;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="companies")
public class CompanyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String cnpj;
	private String address;
	private String phone;

	@OneToMany(mappedBy = "company")
	private Set<ParkingLotEntity> parkingLots;

	@ManyToMany(mappedBy = "companies")
	@Column(nullable = true)
	private Set<UserEntity> users;
	
}
