package com.rafatars.parkingManager.services;

import java.util.List;
import java.util.Optional;

import com.rafatars.parkingManager.entities.VehicleType;
import com.rafatars.parkingManager.entities.dtos.VehicleDTO;

public interface IVehicleService extends IServices<VehicleDTO> {
    Optional<VehicleDTO> findByPlate(String plate);
    List<VehicleDTO> findAllByModel(String model);
    List<VehicleDTO> findAllByBrand(String brand);
    List<VehicleDTO> findAllByColor(String color);
    List<VehicleDTO> findAllByType(VehicleType type);
}
