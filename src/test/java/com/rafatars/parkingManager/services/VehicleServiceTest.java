// package com.rafatars.parkingManager.services;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.rafatars.parkingManager.TestDataUtil;
// import com.rafatars.parkingManager.entities.VehicleEntity;
// import com.rafatars.parkingManager.entities.mirrors.Vehicle;
// import com.rafatars.parkingManager.respositories.IVehicleRepository;
// import com.rafatars.parkingManager.services.impl.VehicleService;

// @ExtendWith(MockitoExtension.class)
// public class VehicleServiceTest {
	
// 	@Mock
// 	private IVehicleRepository vehicleRepository;
	
// 	@InjectMocks
// 	private VehicleService underTest;
	
// 	@Test
// 	public void testThatVehicleCanBeCreated() {
		
// 		final Vehicle vehicle = TestDataUtil.createTestVehicleWithoutCompanyA();
		
// 		final VehicleEntity vehicleEntity = underTest.vehicleToVehicleEntity(vehicle);
		
// 		when(vehicleRepository.save(vehicleEntity)).thenReturn(vehicleEntity);
		
// 		final Vehicle created = underTest.create(vehicle);
		
// 		assertEquals(created, vehicle);
		
// 	}
	
// 	@Test
// 	public void testThatVehicleCanBeUpdated() {
// 		final Vehicle oldVehicle = TestDataUtil.createTestVehicleWithoutCompanyA();
		
// 		Vehicle newVehicle = TestDataUtil.createTestVehicleWithoutCompanyB();
		
// 		VehicleEntity savedMockReturn = underTest.vehicleToVehicleEntity(newVehicle);
// 		savedMockReturn.setId(oldVehicle.getId());
		
// 		when(vehicleRepository.findById(oldVehicle.getId()))
// 			.thenReturn(Optional.of(underTest.vehicleToVehicleEntity(oldVehicle)));
		
// 		when(vehicleRepository.save(savedMockReturn)).thenReturn(savedMockReturn);
		
// 		final Vehicle updatedVehicle = underTest.update(oldVehicle.getId(), newVehicle);
		
// 		newVehicle.setId(oldVehicle.getId());
		
// 		assertEquals(updatedVehicle, newVehicle);
		
// 	}
	
// 	@Test
// 	public void testThatTheVehicleCanBeDeleted() {
// 		final Long id = 1L;
		
// 		underTest.deleteById(id);
		
// 		verify(vehicleRepository, times(1)).deleteById(id);
// 	}
	
// 	@Test
// 	public void testThatCanFindVehicleById() {
		
// 		final Vehicle vehicle = TestDataUtil.createTestVehicleWithoutCompanyA();
		
// 		when(vehicleRepository.findById(vehicle.getId()))
// 			.thenReturn(Optional.of(underTest.vehicleToVehicleEntity(vehicle)));
		
// 		final Optional<Vehicle> foundVehicle = underTest.findById(vehicle.getId());
		
// 		assertThat(foundVehicle.get()).isEqualTo(vehicle);
// 	}
	
// }
