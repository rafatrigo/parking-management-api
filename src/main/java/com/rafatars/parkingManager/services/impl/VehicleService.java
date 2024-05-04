package com.rafatars.parkingManager.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rafatars.parkingManager.entities.VehicleEntity;
import com.rafatars.parkingManager.entities.VehicleType;
import com.rafatars.parkingManager.entities.mirrors.Vehicle;
import com.rafatars.parkingManager.respositories.IVehicleRepository;
import com.rafatars.parkingManager.services.IVehicleService;

import com.rafatars.parkingManager.services.util.ServicesUtils;

@Service
public class VehicleService implements IVehicleService {

	private IVehicleRepository vehicleRepository;
	
	@Autowired
	public VehicleService(IVehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	public Vehicle create(Vehicle obj) {
		
		final VehicleEntity vehicleEntity = ServicesUtils.vehicleToVehicleEntity(obj);
		
		final VehicleEntity savedVehicle = vehicleRepository.save(vehicleEntity);
		
		return ServicesUtils.vehicleEntityToVehicle(savedVehicle);
	}

	@Override
	public Vehicle update(Long id, Vehicle obj) {
		
		VehicleEntity vehicle = vehicleRepository.findById(id).get();
		
		vehicle.setBrand(obj.getBrand());
		vehicle.setColor(obj.getColor());
		vehicle.setModel(obj.getModel());
		vehicle.setPlate(obj.getPlate());
		vehicle.setType(obj.getType());
		
		final VehicleEntity savedVehicle = vehicleRepository.save(vehicle);
		
		return ServicesUtils.vehicleEntityToVehicle(savedVehicle);
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
		
		
		return vehicleEntity.map(vehicle -> ServicesUtils.vehicleEntityToVehicle(vehicle));
	}

	@Override
	public List<Vehicle> FindAll() {
		
		final Iterable<VehicleEntity> vehicleEntities = vehicleRepository.findAll();
		
		final List<VehicleEntity> vehiclesEntitiesList = StreamSupport.stream(vehicleEntities.spliterator(), false)
				.collect(Collectors.toList());
		
		return vehiclesEntitiesList.stream().map(vehicle -> ServicesUtils.vehicleEntityToVehicle(vehicle))
				.collect(Collectors.toList());
	}

	@Override
	public List<Vehicle> findAllByBrand(String brand) {
		
		final List<VehicleEntity> foundVehicleEntity = vehicleRepository.findAllByBrand(brand);
		
		if(foundVehicleEntity.isEmpty()) {
			return List.of();
		}else{
			return foundVehicleEntity.stream().map(vehicle -> ServicesUtils.vehicleEntityToVehicle(vehicle))
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<Vehicle> findAllByColor(String color) {
		
		final List<VehicleEntity> foundVehicleEntity = vehicleRepository.findAllByColor(color);
		
		if(foundVehicleEntity.isEmpty()) {
			return List.of();
		}else{
			return foundVehicleEntity.stream().map(vehicle -> ServicesUtils.vehicleEntityToVehicle(vehicle))
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<Vehicle> findAllByModel(String model) {
		
		final List<VehicleEntity> foundVehicleEntity = vehicleRepository.findAllByModel(model);
		
		if(foundVehicleEntity.isEmpty()) {
			return List.of();
		}else{
			return foundVehicleEntity.stream().map(vehicle -> ServicesUtils.vehicleEntityToVehicle(vehicle))
					.collect(Collectors.toList());
		}
	}

	@Override
	public Optional<Vehicle> findByPlate(String plate) {
		
		final Optional<VehicleEntity> foundVehicleEntity = vehicleRepository.findByPlate(plate);
		
		if(foundVehicleEntity.isEmpty()) {
			return Optional.empty();
		}else{
			return foundVehicleEntity.map(vehicle -> ServicesUtils.vehicleEntityToVehicle(vehicle));
		}
	}

	@Override
	public List<Vehicle> findAllByType(VehicleType type) {
		
		final List<VehicleEntity> foundVehicleEntity = vehicleRepository.findAllByType(type);
		
		if(foundVehicleEntity.isEmpty()) {
			return List.of();
		}else{
			return foundVehicleEntity.stream().map(vehicle -> ServicesUtils.vehicleEntityToVehicle(vehicle))
					.collect(Collectors.toList());
		}
	}
	
}
