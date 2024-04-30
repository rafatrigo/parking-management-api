package com.rafatars.parkingManager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rafatars.parkingManager.entities.mirrors.Vehicle;
import com.rafatars.parkingManager.services.IVehicleService;

@RestController
public class VehicleController {

	private IVehicleService vehicleService;
	
	@Autowired
	public VehicleController(IVehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	
	@GetMapping(path = "/vehicles")
	public List<Vehicle> getVehicles(){
		
		final List<Vehicle> vehicles = vehicleService.FindAll();
 		
		return vehicles;
	}
	
	@GetMapping(path = "/vehicles/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id) {
		
		final Optional<Vehicle> foundVehicle = vehicleService.findById(id);
		
		if(foundVehicle.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundVehicle.get(), HttpStatus.OK);
		}
		
	}

	@GetMapping(path = "/vehicles?plate={plate}")
	public ResponseEntity<Vehicle> getVehicleByPlate(@PathVariable("plate") String plate) {
		
		final Vehicle foundVehicle = vehicleService.findByPlate(plate);
		
		if(foundVehicle == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundVehicle, HttpStatus.OK);
		}
		
	}

	@GetMapping(path = "/vehicles?model={model}")
	public List<Vehicle> getVehicleByModel(@PathVariable("model") String model) {
		
		final List<Vehicle> foundVehicles = vehicleService.findAllByModel(model);
		
		return foundVehicles;
		
	}

	@GetMapping(path = "/vehicles?brand={brand}")
	public List<Vehicle> getVehicleByBrand(@PathVariable("brand") String brand) {
		
		final List<Vehicle> foundVehicles = vehicleService.findAllByBrand(brand);
		
		return foundVehicles;
		
	}

	@GetMapping(path = "/vehicles?color={color}")
	public List<Vehicle> getVehicleByColor(@PathVariable("color") String color) {
		
		final List<Vehicle> foundVehicles = vehicleService.findAllByColor(color);
		
		return foundVehicles;
		
	}

	@GetMapping(path = "/vehicles?type={type}")
	public List<Vehicle> getVehicleByType(@PathVariable("type") String type) {
		
		final List<Vehicle> foundVehicles = vehicleService.findAllByType(type);
		
		return foundVehicles;
		
	}
	
	@PostMapping(path = "/vehicles")
	public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
		
		final Vehicle newVehicle = vehicleService.create(vehicle);
		
		return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
		
	}
	
	@PutMapping(path = "/vehicles/{id}")
	public ResponseEntity<Vehicle> updateVehicle(
			@PathVariable("id") Long id,
			@RequestBody Vehicle vehicle
			){
		final Optional<Vehicle> edtVehicle = vehicleService.findById(id);
		
		if(edtVehicle.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			final Vehicle updatedVehicle = vehicleService.update(id, vehicle);
			
			return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
		}

	}
	
	@DeleteMapping(path = "/vehicles/{id}")
	public <T> ResponseEntity<T> deleteVehicle(@PathVariable("id") Long id) {
		
		final Optional<Vehicle> vehicle = vehicleService.findById(id);
		
		if(vehicle.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			
			vehicleService.deleteById(id);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		
	}
	
}
