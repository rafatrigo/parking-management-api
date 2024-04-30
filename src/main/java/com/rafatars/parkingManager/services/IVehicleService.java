package com.rafatars.parkingManager.services;

import java.util.List;

import com.rafatars.parkingManager.entities.mirrors.Vehicle;

public interface IVehicleService extends IServices<Vehicle> {
    Vehicle findByPlate(String plate);
    List<Vehicle> findAllByModel(String model);
    List<Vehicle> findAllByBrand(String brand);
    List<Vehicle> findAllByColor(String color);
    List<Vehicle> findAllByType(String type);
}
