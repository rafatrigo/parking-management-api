package com.rafatars.parkingManager.TestDataUtils;

import com.rafatars.parkingManager.entities.VehicleEntity;
import com.rafatars.parkingManager.entities.VehicleOwnerEntity;
import com.rafatars.parkingManager.entities.VehicleType;

public class TestDataUtilVehicle {
    
    public static VehicleEntity createTestVehicleEntityA() {
		return VehicleEntity.builder()
				.id(1L)
				.brand("Tesla")
				.model("Model1")
				.color("Black")
				.plate("001")
				.type(VehicleType.CAR)
				.vehicleOwner(TestDataUtilVehicleOwner.createTestVehicleOwnerEntityA())
				.build();
	}

	public static VehicleEntity createTestVehicleEntityAWithoutOwner() {
		return VehicleEntity.builder()
				.id(1L)
				.brand("Tesla")
				.model("Model1")
				.color("Black")
				.plate("001")
				.type(VehicleType.CAR)
				.build();
	}

	public static VehicleEntity createTestVehicleEntitySettingOwnerA(VehicleOwnerEntity owner) {
		return VehicleEntity.builder()
				.id(1L)
				.brand("Tesla")
				.model("Model1")
				.color("Black")
				.plate("001")
				.type(VehicleType.CAR)
				.vehicleOwner(owner)
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
				.vehicleOwner(TestDataUtilVehicleOwner.createTestVehicleOwnerEntityB())
				.build();
	}

	public static VehicleEntity createTestVehicleEntitySettingOwnerB(VehicleOwnerEntity owner) {
		return VehicleEntity.builder()
				.id(2L)
				.brand("Tesla")
				.model("ModelX")
				.color("Orange")
				.plate("002")
				.type(VehicleType.CAR)
				.vehicleOwner(owner)
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
				.vehicleOwner(TestDataUtilVehicleOwner.createTestVehicleOwnerEntityC())
				.build();
	}

	public static VehicleEntity createTestVehicleEntitySettingOwnerC(VehicleOwnerEntity owner) {
		return VehicleEntity.builder()
				.id(3L)
				.brand("Honda")
				.model("whoCares")
				.color("white")
				.plate("003")
				.type(VehicleType.MOTORCYCLE)
				.vehicleOwner(owner)
				.build();
	}

}
