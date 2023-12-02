package com.rafatars.parkingManager.TestDataUtils;

import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.entities.mirrors.Company;

public class TestDataUtilCompany {
    
    public static CompanyEntity createTestCompanyEntityA() {
		
		return CompanyEntity.builder()
				.id(1L)
				.name("CompanyA")
				.cnpj("001")
				.address("Street 01")
				.phone("1231231777")
				.build();
	}
	
	public static CompanyEntity createTestCompanyEntityB() {
		
		return CompanyEntity.builder()
				.id(2L)
				.name("CompanyB")
				.cnpj("002")
				.address("Street 02")
				.phone("1231231888")
				.build();
	}

	public static CompanyEntity createTestCompanyEntityC() {
	
		return CompanyEntity.builder()
			.id(3L)
			.name("CompanyC")
			.cnpj("003")
			.address("Street 03")
			.phone("1231231999")
			.build();
	}
	
	public static Company createTestCompanyA() {
		
		return Company.builder()
				.id(1L)
				.name("CompanyA")
				.cnpj("001")
				.address("Street 01")
				.phone("1231231777")
				.build();
	}
	
	public static Company createTestCompanyB() {
		
		return Company.builder()
				.id(2L)
				.name("CompanyB")
				.cnpj("002")
				.address("Street 02")
				.phone("1231231888")
				.build();
	}
	
	public static Company createTestCompanyC() {
		
		return Company.builder()
			.id(3L)
			.name("CompanyC")
			.cnpj("003")
			.address("Street 03")
			.phone("1231231999")
			.build();
	}

}
