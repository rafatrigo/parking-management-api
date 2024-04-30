package com.rafatars.parkingManager.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.VehicleEntity;

@Repository
public interface IVehicleRepository extends CrudRepository<VehicleEntity, Long> {
    Optional<VehicleEntity> findByPlate(String plate);
    List<VehicleEntity> findAllByModel(String model);
    List<VehicleEntity> findAllByBrand(String brand);
    List<VehicleEntity> findAllByColor(String color);
    List<VehicleEntity> findAllByType(String type);
}
