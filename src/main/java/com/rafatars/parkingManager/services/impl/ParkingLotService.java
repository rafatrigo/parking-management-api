package com.rafatars.parkingManager.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rafatars.parkingManager.entities.ParkingLotEntity;
import com.rafatars.parkingManager.entities.dtos.ParkingLotDTO;
import com.rafatars.parkingManager.respositories.IParkingLotRepository;
import com.rafatars.parkingManager.services.IParkingLotService;
import com.rafatars.parkingManager.services.util.ServicesUtils;

@Service
public class ParkingLotService implements IParkingLotService{
    
    private final IParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotService(IParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingLotDTO create(ParkingLotDTO parkingLot) {
        
        final ParkingLotEntity parkingLotEntity = ServicesUtils.parkingLotToParkingLotEntity(parkingLot);
        
        final ParkingLotEntity savedParkingLot = parkingLotRepository.save(parkingLotEntity);

        return ServicesUtils.parkingLotEntityToParkingLot(savedParkingLot);
        
    }

    @Override
    public void deleteById(Long id) {

        try {
            parkingLotRepository.deleteById(id);

        } catch (final EmptyResultDataAccessException ex) {
            
        }
        
    }

    @Override
    public ParkingLotDTO update(Long id, ParkingLotDTO parkingLot) {
        
        Optional<ParkingLotEntity> updatingParkingLot = parkingLotRepository.findById(id);
        
        if(updatingParkingLot.isEmpty()) {
            return null;
        }

        updatingParkingLot.get().setName(parkingLot.getName());
        updatingParkingLot.get().setAddress(parkingLot.getAddress());
        updatingParkingLot.get().setPhone(parkingLot.getPhone());
        updatingParkingLot.get().setCarSpaces(parkingLot.getCarSpaces());
        updatingParkingLot.get().setMotorcycleSpaces(parkingLot.getMotorcycleSpaces());
        updatingParkingLot.get().setCompany(parkingLot.getCompany());
        
        ParkingLotEntity updatedParkingLot = parkingLotRepository.save(updatingParkingLot.get());
        
        return ServicesUtils.parkingLotEntityToParkingLot(updatedParkingLot);
        
    }

    @Override
    public Optional<ParkingLotDTO> findById(Long id) {
        
        final Optional<ParkingLotEntity> foundParkingLot = parkingLotRepository.findById(id);

        return foundParkingLot.map(parkingLot -> ServicesUtils.parkingLotEntityToParkingLot(parkingLot));
        
    }

    @Override
    public Optional<ParkingLotDTO> findByName(String name) {
        
        final Optional<ParkingLotEntity> foundParkingLot = parkingLotRepository.findByName(name);

        return foundParkingLot.map(parkingLot -> ServicesUtils.parkingLotEntityToParkingLot(parkingLot));
        
    }

    @Override
    public List<ParkingLotDTO> FindAll() {
        
        final Iterable<ParkingLotEntity> foundParkingLots = parkingLotRepository.findAll();
        
        //convert from iterable to list
		final List<ParkingLotEntity> compList = StreamSupport.stream(foundParkingLots.spliterator(), false)
				.collect(Collectors.toList());
		
		//convert list elements from ParkingLotEntity to ParkingLot and returns the list
		return compList.stream()
				.map(parkingLotEntity -> ServicesUtils.parkingLotEntityToParkingLot(parkingLotEntity))
				.collect(Collectors.toList());
        
    }

    @Override
    public List<ParkingLotDTO> findAllByCompany(Long companyId) {
        
        final List<ParkingLotEntity> foundParkingLots = parkingLotRepository.findAllByCompanyId(companyId);

        return foundParkingLots.stream()
                .map(parkingLot -> ServicesUtils.parkingLotEntityToParkingLot(parkingLot))
                .collect(Collectors.toList());
        
    }

    
}
