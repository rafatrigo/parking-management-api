package com.rafatars.parkingManager.TestDataUtils;

import com.rafatars.parkingManager.entities.VehicleOwnerEntity;

public class TestDataUtilVehicleOwner {
    
    public static VehicleOwnerEntity createTestVehicleOwnerEntityA() {
		
		return VehicleOwnerEntity.builder()
				.id(1L)
				.name("OwnerA")
				.phone("1231231888")
				.email("ownerA@gmail.com")
				.build();
	}

	public static VehicleOwnerEntity createTestVehicleOwnerEntityB() {
		
		return VehicleOwnerEntity.builder()
				.id(2L)
				.name("OwnerB")
				.phone("1231231999")
				.email("ownerB@gmail.com")
				.build();
	}

	public static VehicleOwnerEntity createTestVehicleOwnerEntityC() {
		
		return VehicleOwnerEntity.builder()
				.id(3L)
				.name("OwnerC")
				.phone("1231231000")
				.email("ownerC@gmail.com")
				.build();
	}


}
