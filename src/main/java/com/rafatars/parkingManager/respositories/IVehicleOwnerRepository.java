package com.rafatars.parkingManager.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.VehicleOwnerEntity;

@Repository
public interface IVehicleOwnerRepository extends CrudRepository<VehicleOwnerEntity, Long>{
    
}
