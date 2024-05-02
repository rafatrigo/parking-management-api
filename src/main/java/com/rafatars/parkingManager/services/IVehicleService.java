package com.rafatars.parkingManager.services;

import java.util.List;
import java.util.Optional;

import com.rafatars.parkingManager.entities.VehicleType;
import com.rafatars.parkingManager.entities.mirrors.Vehicle;

public interface IVehicleService extends IServices<Vehicle> {
    Optional<Vehicle> findByPlate(String plate);
    List<Vehicle> findAllByModel(String model);
    List<Vehicle> findAllByBrand(String brand);
    List<Vehicle> findAllByColor(String color);
    List<Vehicle> findAllByType(VehicleType type);
}
