package com.rafatars.parkingManager.services.util;

import com.rafatars.parkingManager.dtos.CompanyDTO;
import com.rafatars.parkingManager.dtos.ParkingLotDTO;
import com.rafatars.parkingManager.dtos.VehicleDTO;
import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.entities.ParkingLotEntity;
import com.rafatars.parkingManager.entities.VehicleEntity;

public class ServicesUtils {
    
    public static CompanyDTO companyEntityToCompany(CompanyEntity companyEntity) {
        return CompanyDTO.builder()
				.id(companyEntity.getId())
				.name(companyEntity.getName())
				.cnpj(companyEntity.getCnpj())
				.address(companyEntity.getAddress())
				.phone(companyEntity.getPhone())
				.parkingLots(companyEntity.getParkingLots())
				.users(companyEntity.getUsers())
				.build();
    }

    public static CompanyEntity companyToCompanyEntity(CompanyDTO company) {
        return CompanyEntity.builder()
				.id(company.getId())
				.name(company.getName())
				.cnpj(company.getCnpj())
				.address(company.getAddress())
				.phone(company.getPhone())
				.parkingLots(company.getParkingLots())
				.users(company.getUsers())
				.build();
    }

    public static VehicleEntity vehicleToVehicleEntity(VehicleDTO vehicle) {
		return VehicleEntity.builder()
				.id(vehicle.getId())
				.brand(vehicle.getBrand())
				.color(vehicle.getColor())
				.model(vehicle.getModel())
				.plate(vehicle.getPlate())
				.type(vehicle.getType())
				.build();
		
		
	}
	
	public static VehicleDTO vehicleEntityToVehicle(VehicleEntity vehicle) {
		return VehicleDTO.builder()
				.id(vehicle.getId())
				.brand(vehicle.getBrand())
				.color(vehicle.getColor())
				.model(vehicle.getModel())
				.plate(vehicle.getPlate())
				.type(vehicle.getType())
				.build();
	}

	public static ParkingLotDTO parkingLotEntityToParkingLot(ParkingLotEntity parkingLot) {
		return ParkingLotDTO.builder()
				.id(parkingLot.getId())
				.name(parkingLot.getName())
				.address(parkingLot.getAddress())
				.phone(parkingLot.getPhone())
				.carSpaces(parkingLot.getCarSpaces())
				.motorcycleSpaces(parkingLot.getMotorcycleSpaces())
				.company(parkingLot.getCompany())
				.build();
	}

	public static ParkingLotEntity parkingLotToParkingLotEntity(ParkingLotDTO parkingLot) {
		return ParkingLotEntity.builder()
				.id(parkingLot.getId())
				.name(parkingLot.getName())
				.address(parkingLot.getAddress())
				.phone(parkingLot.getPhone())
				.carSpaces(parkingLot.getCarSpaces())
				.motorcycleSpaces(parkingLot.getMotorcycleSpaces())
				.company(parkingLot.getCompany())
				.build();
	}
    
}
