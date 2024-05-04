package com.rafatars.parkingManager.services;


import java.util.List;
import java.util.Optional;

import com.rafatars.parkingManager.entities.mirrors.ParkingLot;

public interface IParkingLotService extends IServices<ParkingLot>{
        List<ParkingLot> findAllByCompany(Long companyId);
        Optional<ParkingLot> findByName(String name);
}
