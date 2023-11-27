package com.rafatars.parkingManager;

import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.entities.VehicleEntity;
import com.rafatars.parkingManager.entities.VehicleType;
import com.rafatars.parkingManager.entities.mirrors.Company;
import com.rafatars.parkingManager.entities.mirrors.Vehicle;


public final class TestDataUtil {
	
	private TestDataUtil() {
		
	}

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
	
	
	public static VehicleEntity createTestVehicleEntityA() {
		return VehicleEntity.builder()
				.id(1L)
				.brand("Tesla")
				.model("Model1")
				.color("Black")
				.plate("001")
				.type(VehicleType.CAR)
				.company(TestDataUtil.createTestCompanyEntityA())
				.build();
	}
	
	public static VehicleEntity createTestVehicleEntityB() {
		return VehicleEntity.builder()
				.id(2L)
				.brand("Tesla")
				.model("ModelX")
				.color("Orange")
				.plate("002")
				.type(VehicleType.CAR)
				.company(TestDataUtil.createTestCompanyEntityB())
				.build();
	}
	
	public static VehicleEntity createTestVehicleEntityC() {
		return VehicleEntity.builder()
				.id(3L)
				.brand("Honda")
				.model("whoCares")
				.color("white")
				.plate("003")
				.type(VehicleType.MOTORCYCLE)
				.company(TestDataUtil.createTestCompanyEntityC())
				.build();
	}
	
	public static VehicleEntity createTestVehicleEntityWithoutCompany() {
		return VehicleEntity.builder()
				.id(4L)
				.brand("Honda")
				.model("whoCares")
				.color("white")
				.plate("003")
				.type(VehicleType.MOTORCYCLE)
				.build();
	}
	
	public static Vehicle createTestVehicleWithoutCompanyA() {
		return Vehicle.builder()
				.id(1L)
				.brand("Honda")
				.model("whoCares")
				.color("white")
				.plate("003")
				.type(VehicleType.MOTORCYCLE)
				.build();
	}
	
	public static Vehicle createTestVehicleWithoutCompanyB() {
		return Vehicle.builder()
				.id(1L)
				.brand("X")
				.model("whoCaresV3")
				.color("blck")
				.plate("123")
				.type(VehicleType.CAR)
				.build();
	}
	
	public static Vehicle createTestVehicleWithoutCompanyC() {
		return Vehicle.builder()
				.id(1L)
				.brand("Y")
				.model("who")
				.color("blue")
				.plate("5689")
				.type(VehicleType.MOTORCYCLE)
				.build();
	}
	
}
