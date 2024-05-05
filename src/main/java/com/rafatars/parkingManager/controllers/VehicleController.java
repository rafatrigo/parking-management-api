package com.rafatars.parkingManager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rafatars.parkingManager.entities.VehicleType;
import com.rafatars.parkingManager.entities.dtos.VehicleDTO;
import com.rafatars.parkingManager.services.IVehicleService;

@RestController
public class VehicleController {

	private IVehicleService vehicleService;
	
	@Autowired
	public VehicleController(IVehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	
	@GetMapping(path = "/vehicles")
	public List<VehicleDTO> getVehicles(){
		
		final List<VehicleDTO> vehicles = vehicleService.FindAll();
 		
		return vehicles;
	}
	
	@GetMapping(path = "/vehicles/{id}")
	public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable("id") Long id) {
		
		final Optional<VehicleDTO> foundVehicle = vehicleService.findById(id);
		
		if(foundVehicle.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundVehicle.get(), HttpStatus.OK);
		}
		
	}

	@GetMapping(path = "/vehicles", params = "plate")
	public ResponseEntity<VehicleDTO> getVehicleByPlate(@Param("plate") String plate) {
		
		final Optional<VehicleDTO> foundVehicle = vehicleService.findByPlate(plate);
		
		if(foundVehicle.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundVehicle.get(), HttpStatus.OK);
		}
		
	}

	@GetMapping(path = "/vehicles", params = "model")
	public ResponseEntity<List<VehicleDTO>> getVehicleByModel(@Param("model") String model) {
		
		final List<VehicleDTO> foundVehicles = vehicleService.findAllByModel(model);
		
		if(foundVehicles.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundVehicles, HttpStatus.OK);
		}
		
	}

	@GetMapping(path = "/vehicles", params = "brand")
	public ResponseEntity<List<VehicleDTO>> getVehicleByBrand(@Param("brand") String brand) {
		
		final List<VehicleDTO> foundVehicles = vehicleService.findAllByBrand(brand);
		
		if(foundVehicles.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundVehicles, HttpStatus.OK);
		}
		
	}

	@GetMapping(path = "/vehicles", params = "color")
	public ResponseEntity<List<VehicleDTO>> getVehicleByColor(@Param("color") String color) {
		
		final List<VehicleDTO> foundVehicles = vehicleService.findAllByColor(color);
		
		if(foundVehicles.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundVehicles, HttpStatus.OK);
		}
		
	}

	@GetMapping(path = "/vehicles", params = "type")
	public ResponseEntity<List<VehicleDTO>> getVehicleByType(@Param("type") VehicleType type) {
		
		final List<VehicleDTO> foundVehicles = vehicleService.findAllByType(type);
		
		if(foundVehicles.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundVehicles, HttpStatus.OK);
		}
		
	}
	
	@PostMapping(path = "/vehicles")
	public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicle) {
		
		final VehicleDTO newVehicle = vehicleService.create(vehicle);
		
		return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
		
	}
	
	@PutMapping(path = "/vehicles/{id}")
	public ResponseEntity<VehicleDTO> updateVehicle(
			@PathVariable("id") Long id,
			@RequestBody VehicleDTO vehicle
			){
		final Optional<VehicleDTO> edtVehicle = vehicleService.findById(id);
		
		if(edtVehicle.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			final VehicleDTO updatedVehicle = vehicleService.update(id, vehicle);
			
			return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
		}

	}
	
	@DeleteMapping(path = "/vehicles/{id}")
	public ResponseEntity<VehicleDTO> deleteVehicle(@PathVariable("id") Long id) {
		
		final Optional<VehicleDTO> vehicle = vehicleService.findById(id);
		
		if(vehicle.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			
			vehicleService.deleteById(id);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		
	}
	
}
