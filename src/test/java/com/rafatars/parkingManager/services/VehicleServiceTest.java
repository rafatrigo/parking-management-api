package com.rafatars.parkingManager.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rafatars.parkingManager.TestDataUtils.TestDataUtilVehicle;
import com.rafatars.parkingManager.dtos.VehicleDTO;
import com.rafatars.parkingManager.entities.VehicleEntity;
import com.rafatars.parkingManager.entities.VehicleType;
import com.rafatars.parkingManager.respositories.IVehicleRepository;
import com.rafatars.parkingManager.services.impl.VehicleService;
import com.rafatars.parkingManager.services.util.ServicesUtils;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
	
	@Mock
	private IVehicleRepository vehicleRepository;
	
	@InjectMocks
	private VehicleService underTest;
	
	@Test
    public void testThatVehicleCanBeCreated() {

        final VehicleEntity vehicleEntity = TestDataUtilVehicle.createTestVehicleEntityA();
        final VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(vehicleEntity);
        
        when(vehicleRepository.save(vehicleEntity)).thenReturn(vehicleEntity);

        final VehicleDTO createdVehicle = underTest.create(vehicle);

        assertEquals(vehicle, createdVehicle);
        
    }

    @Test
    public void testThatVehicleCanBeDeleted() {

        final Long id = 1L;
		
		underTest.deleteById(id);
		verify(vehicleRepository, times(1)).deleteById(id);
        
    }
	
    @Test
    public void testThatVehicleCanBeUpdated() {

       final VehicleDTO oldVehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
		
		VehicleDTO newVehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityB());
		
		VehicleEntity saveMockReturn = ServicesUtils.vehicleToVehicleEntity(newVehicle);
		
		saveMockReturn.setId(oldVehicle.getId());
		
		when(vehicleRepository.findById(oldVehicle.getId()))
			.thenReturn(Optional.of(ServicesUtils.vehicleToVehicleEntity(oldVehicle)));
		
		when(vehicleRepository.save(saveMockReturn)).thenReturn(saveMockReturn);
		
		final VehicleDTO updatedVehicle = underTest.update(oldVehicle.getId(), newVehicle);
		
		newVehicle.setId(oldVehicle.getId());
		
		assertEquals(updatedVehicle, newVehicle);
        
    }

    @Test
    public void testThatCanFindVehicleById() {

        final VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());

        when(vehicleRepository.findById(vehicle.getId()))
            .thenReturn(Optional.of(ServicesUtils.vehicleToVehicleEntity(vehicle)));

        final Optional<VehicleDTO> foundVehicle = underTest.findById(vehicle.getId());

        assertThat(foundVehicle.get()).isEqualTo(vehicle);
        
    }

    @Test
    public void testThatIfVehicleNotFoundReturnsAnEmptyOptional() {

        final Long id = 1L;

        when(vehicleRepository.findById(id))
            .thenReturn(Optional.empty());

        final Optional<VehicleDTO> result = underTest.findById(id);

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatCanFindAllVehicles() {

        final VehicleEntity vehicleEntityA = TestDataUtilVehicle.createTestVehicleEntityA();
        final VehicleEntity vehicleEntityB = TestDataUtilVehicle.createTestVehicleEntityB();

        ArrayList<VehicleEntity> vehicles = new ArrayList<>();

        vehicles.add(vehicleEntityA);
        vehicles.add(vehicleEntityB);

        when(vehicleRepository.findAll())
            .thenReturn(vehicles);

        final int expectedSize = vehicles.size();
        final int actualSize = underTest.FindAll().size();

        assertEquals(expectedSize, actualSize);
        
    }

    @Test
    public void testThatCanFindVehicleByPlate() {

        final VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());

        when(vehicleRepository.findByPlate(vehicle.getPlate()))
            .thenReturn(Optional.of(ServicesUtils.vehicleToVehicleEntity(vehicle)));

        final Optional<VehicleDTO> foundVehicle = underTest.findByPlate(vehicle.getPlate());

        assertThat(foundVehicle.get()).isEqualTo(vehicle);
        
    }

    @Test
    public void testThatIfCantFindVehicleByPlateReturnsEmptyOptional() {

        final String plate = "ABC123";

        when(vehicleRepository.findByPlate(plate))
            .thenReturn(Optional.empty());

        final Optional<VehicleDTO> result = underTest.findByPlate(plate);

        assertThat(result).isEmpty();
        
    }

    @Test
    public void testThatCanFindAllVehiclesByType() {

        final VehicleEntity vehicleEntityA = TestDataUtilVehicle.createTestVehicleEntityA();
        final VehicleEntity vehicleEntityB = TestDataUtilVehicle.createTestVehicleEntityB();

        ArrayList<VehicleEntity> vehicles = new ArrayList<>();

        vehicles.add(vehicleEntityA);
        vehicles.add(vehicleEntityB);

        when(vehicleRepository.findAllByType(vehicleEntityA.getType()))
            .thenReturn(vehicles);

        final int expectedSize = vehicles.size();
        final int actualSize = underTest.findAllByType(vehicleEntityA.getType()).size();

        assertEquals(expectedSize, actualSize);
        
    }

    @Test
    public void testThatCantFindAllVehiclesByTypeReturnsEmptyList() {

        final VehicleType type = VehicleType.CAR;

        when(vehicleRepository.findAllByType(type))
            .thenReturn(new ArrayList<>());

        final int result = underTest.findAllByType(type).size();

        assertEquals(0, result);
        
    }

    @Test
    public void testThatCanFindAllVehiclesByColor() {

        final VehicleEntity vehicleEntityA = TestDataUtilVehicle.createTestVehicleEntityA();

        ArrayList<VehicleEntity> vehicles = new ArrayList<>();

        vehicles.add(vehicleEntityA);

        when(vehicleRepository.findAllByColor(vehicleEntityA.getColor()))
            .thenReturn(vehicles);

        final int expectedSize = vehicles.size();
        final int actualSize = underTest.findAllByColor(vehicleEntityA.getColor()).size();

        assertEquals(expectedSize, actualSize);
        
    }

    @Test
    public void testThatIfCantFindVehiclesByColorReturnsEmptyList() {

        final String color = "Black";

        when(vehicleRepository.findAllByColor(color))
            .thenReturn(new ArrayList<>());

        final int result = underTest.findAllByColor(color).size();

        assertEquals(0, result);
        
    }

    @Test
    public void testThatCanFindAllVehiclesByBrand() {

        final VehicleEntity vehicleEntityA = TestDataUtilVehicle.createTestVehicleEntityA();
        final VehicleEntity vehicleEntityB = TestDataUtilVehicle.createTestVehicleEntityB();

        ArrayList<VehicleEntity> vehicles = new ArrayList<>();

        vehicles.add(vehicleEntityA);
        vehicles.add(vehicleEntityB);

        when(vehicleRepository.findAllByBrand(vehicleEntityA.getBrand()))
            .thenReturn(vehicles);

        final int expectedSize = vehicles.size();
        final int actualSize = underTest.findAllByBrand(vehicleEntityA.getBrand()).size();

        assertEquals(expectedSize, actualSize);
        
    }

    @Test
    public void testThatIfCantFindVehiclesByBrandReturnsEmptyList() {

        final String brand = "NewBrand";

        when(vehicleRepository.findAllByBrand(brand))
            .thenReturn(new ArrayList<>());

        final int result = underTest.findAllByBrand(brand).size();

        assertEquals(0, result);
        
    }

    @Test
    public void testThatCanFindAllVehiclesByModel() {

        final VehicleEntity vehicleEntityA = TestDataUtilVehicle.createTestVehicleEntityA();
        final VehicleEntity vehicleEntityB = TestDataUtilVehicle.createTestVehicleEntityB();

        ArrayList<VehicleEntity> vehicles = new ArrayList<>();

        vehicles.add(vehicleEntityA);
        vehicles.add(vehicleEntityB);

        when(vehicleRepository.findAllByModel(vehicleEntityA.getModel()))
            .thenReturn(vehicles);

        final int expectedSize = vehicles.size();
        final int actualSize = underTest.findAllByModel(vehicleEntityA.getModel()).size();

        assertEquals(expectedSize, actualSize);
        
    }

    @Test
    public void testThatIfCantFindVehiclesByModelReturnsEmptyList() {

        final String model = "NewModel";

        when(vehicleRepository.findAllByModel(model))
            .thenReturn(new ArrayList<>());

        final int result = underTest.findAllByModel(model).size();

        assertEquals(0, result);
        
    }

    
}
