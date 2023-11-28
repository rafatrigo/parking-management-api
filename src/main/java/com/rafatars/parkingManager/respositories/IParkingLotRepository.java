package com.rafatars.parkingManager.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.ParkingLotEntity;

@Repository
public interface IParkingLotRepository extends CrudRepository<ParkingLotEntity, Long>{
    
}
