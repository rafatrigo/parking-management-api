package com.rafatars.parkingManager.TestDataUtils;

import com.rafatars.parkingManager.entities.ParkingLotEntity;

public class TestDataUtilParkingLot {
    
    public static ParkingLotEntity createTestParkingLotEntityA() {
		
		return ParkingLotEntity.builder()
				.id(1L)
				.name("PLA")
				.address("Street 01")
				.phone("1231231777")
				.carSpaces(20)
				.motorcycleSpaces(10)
				.company(TestDataUtilCompany.createTestCompanyEntityA())
				.build();
	}

	public static ParkingLotEntity createTestParkingLotEntityB() {
		
		return ParkingLotEntity.builder()
				.id(2L)
				.name("PLB")
				.address("Street 02")
				.phone("1231231888")
				.carSpaces(30)
				.motorcycleSpaces(45)
				.company(TestDataUtilCompany.createTestCompanyEntityB())
				.build();
	}

}
