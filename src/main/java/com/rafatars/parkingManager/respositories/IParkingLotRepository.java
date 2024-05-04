package com.rafatars.parkingManager.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.ParkingLotEntity;

@Repository
public interface IParkingLotRepository extends CrudRepository<ParkingLotEntity, Long>{
    List<ParkingLotEntity> findAllByCompanyId(Long companyId);
    Optional<ParkingLotEntity> findByName(String name);
}
