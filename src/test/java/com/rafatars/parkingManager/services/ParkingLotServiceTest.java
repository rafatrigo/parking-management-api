package com.rafatars.parkingManager.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rafatars.parkingManager.TestDataUtils.TestDataUtilParkingLot;
import com.rafatars.parkingManager.entities.ParkingLotEntity;
import com.rafatars.parkingManager.entities.mirrors.ParkingLot;
import com.rafatars.parkingManager.respositories.IParkingLotRepository;
import com.rafatars.parkingManager.services.impl.ParkingLotService;
import com.rafatars.parkingManager.services.util.ServicesUtils;

@ExtendWith(MockitoExtension.class)
public class ParkingLotServiceTest {
    
    @Mock
    private IParkingLotRepository parkingLotRepository;

    @InjectMocks
    private ParkingLotService underTest;

    @Test
    public void testThatParkingLotIsCreated() {
        final ParkingLotEntity parkingLotEntity = TestDataUtilParkingLot.createTestParkingLotEntityA();
        final ParkingLot parkingLot = ServicesUtils.parkingLotEntityToParkingLot(parkingLotEntity);

        when(parkingLotRepository.save(eq(parkingLotEntity))).thenReturn(parkingLotEntity);
        
        final ParkingLot result = underTest.create(parkingLot);

        assertEquals(parkingLot, result);
        
    }

    @Test
    public void testThatParkingLotHasBeenDeleted() {
        final Long id = 1L;
		
		underTest.deleteById(id);
		verify(parkingLotRepository, times(1)).deleteById(id);
        
    }

    @Test
    public void testThatParkingLotHasBeenUpdated() {
        
        final ParkingLot oldParkingLot = ServicesUtils.parkingLotEntityToParkingLot(TestDataUtilParkingLot.createTestParkingLotEntityA());

        ParkingLot newParkingLot = ServicesUtils.parkingLotEntityToParkingLot(TestDataUtilParkingLot.createTestParkingLotEntityB());
        
        ParkingLotEntity saveMockReturn = ServicesUtils.parkingLotToParkingLotEntity(newParkingLot);

        saveMockReturn.setId(oldParkingLot.getId());

        when(parkingLotRepository.findById(eq(oldParkingLot.getId())))
            .thenReturn(Optional.of(ServicesUtils.parkingLotToParkingLotEntity(oldParkingLot)));
        
        when(parkingLotRepository.save(eq(saveMockReturn)))
            .thenReturn(saveMockReturn);

        final ParkingLot result = underTest.update(oldParkingLot.getId(), newParkingLot);

        newParkingLot.setId(oldParkingLot.getId());
        assertEquals(newParkingLot, result);
    }

    @Test
    public void testThatCanFindParkingLotById() {
        final ParkingLotEntity parkingLotEntity = TestDataUtilParkingLot.createTestParkingLotEntityA();
        final ParkingLot parkingLot = ServicesUtils.parkingLotEntityToParkingLot(parkingLotEntity);

        when(parkingLotRepository.findById(eq(parkingLot.getId())))
            .thenReturn(Optional.of(parkingLotEntity));

        final Optional<ParkingLot> result = underTest.findById(parkingLot.getId());

        assertEquals(parkingLot, result.get());
    }

    @Test
    public void testThatIfCantParkingLotByIdReturnEmpty() {
        final ParkingLotEntity parkingLotEntity = TestDataUtilParkingLot.createTestParkingLotEntityA();
        final ParkingLot parkingLot = ServicesUtils.parkingLotEntityToParkingLot(parkingLotEntity);

        when(parkingLotRepository.findById(eq(parkingLot.getId())))
            .thenReturn(Optional.empty());

        final Optional<ParkingLot> result = underTest.findById(parkingLot.getId());

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatCanFindParkingLotByName() {
        final ParkingLotEntity parkingLotEntity = TestDataUtilParkingLot.createTestParkingLotEntityA();
        final ParkingLot parkingLot = ServicesUtils.parkingLotEntityToParkingLot(parkingLotEntity);

        when(parkingLotRepository.findByName(eq(parkingLot.getName())))
            .thenReturn(Optional.of(parkingLotEntity));

        final Optional<ParkingLot> result = underTest.findByName(parkingLot.getName());

        assertEquals(parkingLot, result.get());
    }

    @Test
    public void testThatIfCantFindParkingLotByNameReturnEmpty() {

        when(parkingLotRepository.findByName(eq("ParkinLotName")))
            .thenReturn(Optional.empty());

        final Optional<ParkingLot> result = underTest.findByName("ParkinLotName");

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatCanFindAllParkingLotsByCompany() {
        final ParkingLotEntity parkingLotEntityA = TestDataUtilParkingLot.createTestParkingLotEntityA();
        final ParkingLotEntity parkingLotEntityB = TestDataUtilParkingLot.createTestParkingLotEntityB();

        List<ParkingLotEntity> parkingLotEntities = List.of(parkingLotEntityA, parkingLotEntityB);

        when(parkingLotRepository.findAllByCompanyId(eq(1L)))
            .thenReturn(parkingLotEntities);

        final List<ParkingLot> result = underTest.findAllByCompany(1L);

        assertThat(result).hasSize(2)
            .containsExactly(ServicesUtils.parkingLotEntityToParkingLot(parkingLotEntityA), 
                ServicesUtils.parkingLotEntityToParkingLot(parkingLotEntityB));
    }

    @Test
    public void testThatCanFindAllParkingLots() {
        final ParkingLotEntity parkingLotEntityA = TestDataUtilParkingLot.createTestParkingLotEntityA();
        final ParkingLotEntity parkingLotEntityB = TestDataUtilParkingLot.createTestParkingLotEntityB();

        List<ParkingLotEntity> parkingLotEntities = List.of(parkingLotEntityA, parkingLotEntityB);

        when(parkingLotRepository.findAll())
            .thenReturn(parkingLotEntities);

        final List<ParkingLot> result = underTest.FindAll();

        assertThat(result).hasSize(2)
            .containsExactly(ServicesUtils.parkingLotEntityToParkingLot(parkingLotEntityA), 
                ServicesUtils.parkingLotEntityToParkingLot(parkingLotEntityB));
    }
    
}
