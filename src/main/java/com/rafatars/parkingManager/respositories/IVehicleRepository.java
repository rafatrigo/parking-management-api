package com.rafatars.parkingManager.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.VehicleEntity;

@Repository
public interface IVehicleRepository extends CrudRepository<VehicleEntity, Long> {

}
