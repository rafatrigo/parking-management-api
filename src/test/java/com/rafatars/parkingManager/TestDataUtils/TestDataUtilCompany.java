package com.rafatars.parkingManager.TestDataUtils;

import java.util.HashSet;
import java.util.Set;

import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.entities.ParkingLotEntity;
import com.rafatars.parkingManager.entities.mirrors.Company;

public class TestDataUtilCompany {
    
    public static CompanyEntity createTestCompanyEntityA() {
		
		Set<ParkingLotEntity> parkingLots = new HashSet<>();
		
		return CompanyEntity.builder()
				.id(1L)
				.name("CompanyA")
				.cnpj("001")
				.address("Street 01")
				.phone("1231231777")
				.parkingLots(parkingLots)
				.build();
	}
	
	public static CompanyEntity createTestCompanyEntityB() {
		
		Set<ParkingLotEntity> parkingLots = new HashSet<>();
		
		return CompanyEntity.builder()
				.id(2L)
				.name("CompanyB")
				.cnpj("002")
				.address("Street 02")
				.phone("1231231888")
				.parkingLots(parkingLots)
				.build();
	}

	public static CompanyEntity createTestCompanyEntityC() {
	
		Set<ParkingLotEntity> parkingLots = new HashSet<>();
		
		return CompanyEntity.builder()
			.id(3L)
			.name("CompanyC")
			.cnpj("003")
			.address("Street 03")
			.phone("1231231999")
			.parkingLots(parkingLots)
			.build();
	}
	
	public static Company createTestCompanyA() {
		
		Set<ParkingLotEntity> parkingLots = new HashSet<>();
		
		return Company.builder()
				.id(1L)
				.name("CompanyA")
				.cnpj("001")
				.address("Street 01")
				.phone("1231231777")
				.parkingLots(parkingLots)
				.build();
	}
	
	public static Company createTestCompanyB() {
		
		Set<ParkingLotEntity> parkingLots = new HashSet<>();
		
		return Company.builder()
				.id(2L)
				.name("CompanyB")
				.cnpj("002")
				.address("Street 02")
				.phone("1231231888")
				.parkingLots(parkingLots)
				.build();
	}
	
	public static Company createTestCompanyC() {
		
		Set<ParkingLotEntity> parkingLots = new HashSet<>();
		
		return Company.builder()
			.id(3L)
			.name("CompanyC")
			.cnpj("003")
			.address("Street 03")
			.phone("1231231999")
			.parkingLots(parkingLots)
			.build();
	}

}
