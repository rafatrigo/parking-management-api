package com.rafatars.parkingManager.services;


import java.util.List;
import java.util.Optional;

import com.rafatars.parkingManager.dtos.ParkingLotDTO;

public interface IParkingLotService extends IServices<ParkingLotDTO>{
        List<ParkingLotDTO> findAllByCompany(Long companyId);
        Optional<ParkingLotDTO> findByName(String name);
}
