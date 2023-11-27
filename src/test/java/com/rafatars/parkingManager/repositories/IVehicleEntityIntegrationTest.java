package com.rafatars.parkingManager.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rafatars.parkingManager.TestDataUtil;
import com.rafatars.parkingManager.entities.VehicleEntity;
import com.rafatars.parkingManager.respositories.IVehicleRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class IVehicleEntityIntegrationTest {

	private IVehicleRepository underTest;

	@Autowired
	public IVehicleEntityIntegrationTest(IVehicleRepository underTest) {
		this.underTest = underTest;
	}
	
	
	@Test
	public void testThatVehicleCanBeCreatedAndRecalled() {
		
		VehicleEntity vehicleEntity = TestDataUtil.createTestVehicleEntityA();
		
		underTest.save(vehicleEntity);
		
		Optional<VehicleEntity> result = underTest.findById(vehicleEntity.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(vehicleEntity);
		
	}
	
	@Test
	public void testThatVehicleCanBeCreatedWithoutCompany() {
		VehicleEntity vehicleEntity = TestDataUtil.createTestVehicleEntityWithoutCompany();
		
		underTest.save(vehicleEntity);
		
		Optional<VehicleEntity> result = underTest.findById(vehicleEntity.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(vehicleEntity);
	}
	
	@Test
	public void testThatMultiplesVehiclesCanBeCreatedAndRecalled() {
		
		VehicleEntity vehicle1 = TestDataUtil.createTestVehicleEntityA();
		underTest.save(vehicle1);
		VehicleEntity vehicle2 = TestDataUtil.createTestVehicleEntityB();
		underTest.save(vehicle2);
		VehicleEntity vehicle3 = TestDataUtil.createTestVehicleEntityC();
		underTest.save(vehicle3);
		
		
		
		Iterable<VehicleEntity> result = underTest.findAll();
		
		assertThat(result).hasSize(3).containsExactly(vehicle1, vehicle2, vehicle3);
		
	}
	
	
	@Test
	public void testThatVehicleCanBeUpdated() {
		
		VehicleEntity vehicleEntity = TestDataUtil.createTestVehicleEntityA();
		underTest.save(vehicleEntity);
		
		vehicleEntity.setModel("updated");
		underTest.save(vehicleEntity);
		
		Optional<VehicleEntity> result = underTest.findById(vehicleEntity.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(vehicleEntity);
		
	}
	
	@Test
	public void testThatVehicleCanBeDeleted() {
		VehicleEntity vehicleEntity = TestDataUtil.createTestVehicleEntityA();
		underTest.save(vehicleEntity);
		
		underTest.deleteById(vehicleEntity.getId());
		
		Optional<VehicleEntity> result = underTest.findById(vehicleEntity.getId());
		
		
		assertThat(result).isEmpty();
	}
	
	
}
