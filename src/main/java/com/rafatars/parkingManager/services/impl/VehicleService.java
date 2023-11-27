package com.rafatars.parkingManager.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rafatars.parkingManager.entities.Vehicle;
import com.rafatars.parkingManager.entities.VehicleEntity;
import com.rafatars.parkingManager.respositories.IVehicleRepository;
import com.rafatars.parkingManager.services.IVehicleService;

@Service
public class VehicleService implements IVehicleService {

	private IVehicleRepository vehicleRepository;
	
	@Autowired
	public VehicleService(IVehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	
	
	@Override
	public Vehicle create(Vehicle obj) {
		
		final VehicleEntity vehicleEntity = vehicleToVehicleEntity(obj);
		
		final VehicleEntity savedVehicle = vehicleRepository.save(vehicleEntity);
		
		return vehicleEntityToVehicle(savedVehicle);
	}

	@Override
	public Vehicle update(Long id, Vehicle obj) {
		
		VehicleEntity vehicle = vehicleRepository.findById(id).get();
		
		vehicle.setBrand(obj.getBrand());
		vehicle.setColor(obj.getColor());
		vehicle.setModel(obj.getModel());
		vehicle.setPlate(obj.getPlate());
		vehicle.setType(obj.getType());
		vehicle.setCompany(obj.getCompany());
		
		final VehicleEntity savedVehicle = vehicleRepository.save(vehicle);
		
		return vehicleEntityToVehicle(savedVehicle);
	}

	@Override
	public void deleteById(Long id) {
		
		try {
			vehicleRepository.deleteById(id);

	    } catch (final EmptyResultDataAccessException ex) {
	     
	    }
		
	}

	@Override
	public Optional<Vehicle> findById(Long id) {
		
		Optional<VehicleEntity> vehicleEntity = vehicleRepository.findById(id);
		
		
		return vehicleEntity.map(vehicle -> vehicleEntityToVehicle(vehicle));
	}

	@Override
	public List<Vehicle> FindAll() {
		
		final Iterable<VehicleEntity> vehicleEntities = vehicleRepository.findAll();
		
		final List<VehicleEntity> vehiclesEntitiesList = StreamSupport.stream(vehicleEntities.spliterator(), false)
				.collect(Collectors.toList());
		
		return vehiclesEntitiesList.stream().map(vehicle -> vehicleEntityToVehicle(vehicle))
				.collect(Collectors.toList());
	}
	
	
	public VehicleEntity vehicleToVehicleEntity(Vehicle vehicle) {
		return VehicleEntity.builder()
				.id(vehicle.getId())
				.brand(vehicle.getBrand())
				.color(vehicle.getColor())
				.model(vehicle.getModel())
				.plate(vehicle.getPlate())
				.type(vehicle.getType())
				.company(vehicle.getCompany())
				.build();
		
		
	}
	
	public Vehicle vehicleEntityToVehicle(VehicleEntity vehicle) {
		return Vehicle.builder()
				.id(vehicle.getId())
				.brand(vehicle.getBrand())
				.color(vehicle.getColor())
				.model(vehicle.getModel())
				.plate(vehicle.getPlate())
				.type(vehicle.getType())
				.company(vehicle.getCompany())
				.build();
	}
	
	
}
