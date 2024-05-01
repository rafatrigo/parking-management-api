package com.rafatars.parkingManager.services.util;

import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.entities.VehicleEntity;
import com.rafatars.parkingManager.entities.mirrors.Company;
import com.rafatars.parkingManager.entities.mirrors.Vehicle;

public class ServicesUtils {
    
    public static Company companyEntityToCompany(CompanyEntity companyEntity) {
        return Company.builder()
				.id(companyEntity.getId())
				.name(companyEntity.getName())
				.cnpj(companyEntity.getCnpj())
				.address(companyEntity.getAddress())
				.phone(companyEntity.getPhone())
				.parkingLots(companyEntity.getParkingLots())
				.users(companyEntity.getUsers())
				.build();
    }

    public static CompanyEntity companyToCompanyEntity(Company company) {
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

    public static VehicleEntity vehicleToVehicleEntity(Vehicle vehicle) {
		return VehicleEntity.builder()
				.id(vehicle.getId())
				.brand(vehicle.getBrand())
				.color(vehicle.getColor())
				.model(vehicle.getModel())
				.plate(vehicle.getPlate())
				.type(vehicle.getType())
				.build();
		
		
	}
	
	public static Vehicle vehicleEntityToVehicle(VehicleEntity vehicle) {
		return Vehicle.builder()
				.id(vehicle.getId())
				.brand(vehicle.getBrand())
				.color(vehicle.getColor())
				.model(vehicle.getModel())
				.plate(vehicle.getPlate())
				.type(vehicle.getType())
				.build();
	}
    
}
