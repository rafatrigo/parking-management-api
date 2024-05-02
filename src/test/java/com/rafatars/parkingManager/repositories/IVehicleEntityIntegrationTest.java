package com.rafatars.parkingManager.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rafatars.parkingManager.TestDataUtils.TestDataUtilVehicle;
import com.rafatars.parkingManager.entities.VehicleEntity;
import com.rafatars.parkingManager.entities.VehicleType;
import com.rafatars.parkingManager.respositories.IVehicleRepository;

import jakarta.transaction.Transactional;

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
    @Transactional //to avoid lazyInitializationExeption
	public void testThatVehicleCanBeCreatedAndRecalled() {

        //create and save a vehicle

		VehicleEntity vehicleEntity = TestDataUtilVehicle.createTestVehicleEntityA();
		
		underTest.save(vehicleEntity);
		
        //----------------

        //test if the vehicle was created and can be recalled

		Optional<VehicleEntity> result = underTest.findById(vehicleEntity.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(vehicleEntity);
		
	}
	
	@Test
    @Transactional //to avoid lazyInitializationExeption
	public void testThatMultiplesVehiclesCanBeCreatedAndRecalled() {

        //create and save a vehicle

		VehicleEntity vehicleEntity1 = TestDataUtilVehicle.createTestVehicleEntityA();
		VehicleEntity vehicleEntity2 = TestDataUtilVehicle.createTestVehicleEntityB();
        VehicleEntity vehicleEntity3 = TestDataUtilVehicle.createTestVehicleEntityC();

		underTest.save(vehicleEntity1);
        underTest.save(vehicleEntity2);
		underTest.save(vehicleEntity3);
		
        //----------------
		
		Iterable<VehicleEntity> result = underTest.findAll();
		
		assertThat(result).hasSize(3).containsExactly(vehicleEntity1, vehicleEntity2, vehicleEntity3);
		
	}
	
	
	@Test
	public void testThatVehicleCanBeUpdated() {
		
		VehicleEntity vehicleEntity = TestDataUtilVehicle.createTestVehicleEntityA();
		underTest.save(vehicleEntity);
		
		vehicleEntity.setModel("updated");
		underTest.save(vehicleEntity);
		
		Optional<VehicleEntity> result = underTest.findById(vehicleEntity.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(vehicleEntity);
		
	}
	
	@Test
	public void testThatVehicleCanBeDeleted() {
		VehicleEntity vehicleEntity = TestDataUtilVehicle.createTestVehicleEntityA();
		underTest.save(vehicleEntity);
		
		underTest.deleteById(vehicleEntity.getId());
		
		Optional<VehicleEntity> result = underTest.findById(vehicleEntity.getId());
		
		
		assertThat(result).isEmpty();
	}

	@Test
	public void testThatCanFindVehicleByPlate() {
		VehicleEntity vehicleEntity = TestDataUtilVehicle.createTestVehicleEntityA();
		underTest.save(vehicleEntity);
		
		Optional<VehicleEntity> result = underTest.findByPlate(vehicleEntity.getPlate());
		
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(vehicleEntity);
	}

	@Test
	public void testThatIfCantFindVehicleByPlateReturnEmpty() {
		VehicleEntity vehicleEntity = TestDataUtilVehicle.createTestVehicleEntityA();
		underTest.save(vehicleEntity);
		
		Optional<VehicleEntity> result = underTest.findByPlate("notExistingPlate");
		
		assertThat(result).isEmpty();
	}

	@Test
	public void testThatCanFindAllVehiclesByType() {
		VehicleEntity vehicleEntity1 = TestDataUtilVehicle.createTestVehicleEntityA();
		VehicleEntity vehicleEntity2 = TestDataUtilVehicle.createTestVehicleEntityB();
		VehicleEntity vehicleEntity3 = TestDataUtilVehicle.createTestVehicleEntityC();
		underTest.save(vehicleEntity1);
		underTest.save(vehicleEntity2);
		underTest.save(vehicleEntity3);
		
		List<VehicleEntity> result = underTest.findAllByType(vehicleEntity1.getType());
		
		assertThat(result).hasSize(2).containsExactly(vehicleEntity1, vehicleEntity2);
	}
	
	@Test
	public void testThatIfCantFindVehicleByTypeReturnEmpty() {
		VehicleEntity vehicleEntity1 = TestDataUtilVehicle.createTestVehicleEntityA();
		VehicleEntity vehicleEntity2 = TestDataUtilVehicle.createTestVehicleEntityB();
		underTest.save(vehicleEntity1);
		underTest.save(vehicleEntity2);
		
		List<VehicleEntity> result = underTest.findAllByType(VehicleType.MOTORCYCLE);
		
		assertThat(result).isEmpty();
	}

	@Test
	public void testThatCanFindVehicleByColor() {
		VehicleEntity vehicleEntity1 = TestDataUtilVehicle.createTestVehicleEntityA();
		VehicleEntity vehicleEntity2 = TestDataUtilVehicle.createTestVehicleEntityB();
		VehicleEntity vehicleEntity3 = TestDataUtilVehicle.createTestVehicleEntityC();
		underTest.save(vehicleEntity1);
		underTest.save(vehicleEntity2);
		underTest.save(vehicleEntity3);
		
		List<VehicleEntity> result = underTest.findAllByColor(vehicleEntity1.getColor());
		
		assertThat(result).hasSize(1).containsExactly(vehicleEntity1);
	}
	
	@Test
	public void testThatIfCantFindVehicleByColorReturnEmpty() {
		VehicleEntity vehicleEntity1 = TestDataUtilVehicle.createTestVehicleEntityA();
		VehicleEntity vehicleEntity2 = TestDataUtilVehicle.createTestVehicleEntityB();
		underTest.save(vehicleEntity1);
		underTest.save(vehicleEntity2);
		
		List<VehicleEntity> result = underTest.findAllByColor("notExistingColor");
		
		assertThat(result).isEmpty();
	}

	@Test
	public void testThatCanFindAllVehiclesByBrand() {
		VehicleEntity vehicleEntity1 = TestDataUtilVehicle.createTestVehicleEntityA();
		VehicleEntity vehicleEntity2 = TestDataUtilVehicle.createTestVehicleEntityB();
		VehicleEntity vehicleEntity3 = TestDataUtilVehicle.createTestVehicleEntityC();
		underTest.save(vehicleEntity1);
		underTest.save(vehicleEntity2);
		underTest.save(vehicleEntity3);
		
		List<VehicleEntity> result = underTest.findAllByBrand(vehicleEntity1.getBrand());
		
		assertThat(result).hasSize(2).containsExactly(vehicleEntity1, vehicleEntity2);
	}
	
	@Test
	public void testThatIfCantFindVehicleByBrandReturnEmpty() {
		VehicleEntity vehicleEntity1 = TestDataUtilVehicle.createTestVehicleEntityA();
		VehicleEntity vehicleEntity2 = TestDataUtilVehicle.createTestVehicleEntityB();
		underTest.save(vehicleEntity1);
		underTest.save(vehicleEntity2);
		
		List<VehicleEntity> result = underTest.findAllByBrand("notExistingBrand");
		
		assertThat(result).isEmpty();
	}
	
	@Test
	public void testThatCanFindAllVehiclesByModel() {
		VehicleEntity vehicleEntity1 = TestDataUtilVehicle.createTestVehicleEntityA();
		VehicleEntity vehicleEntity2 = TestDataUtilVehicle.createTestVehicleEntityB();
		VehicleEntity vehicleEntity3 = TestDataUtilVehicle.createTestVehicleEntityC();
		underTest.save(vehicleEntity1);
		underTest.save(vehicleEntity2);
		underTest.save(vehicleEntity3);
		
		List<VehicleEntity> result = underTest.findAllByModel(vehicleEntity2.getModel());
		
		assertThat(result).hasSize(1).containsExactly(vehicleEntity2);
	}

	@Test
	public void testThatIfCantFindVehicleByModelReturnEmpty() {
		VehicleEntity vehicleEntity1 = TestDataUtilVehicle.createTestVehicleEntityA();
		VehicleEntity vehicleEntity2 = TestDataUtilVehicle.createTestVehicleEntityB();
		underTest.save(vehicleEntity1);
		underTest.save(vehicleEntity2);
		
		List<VehicleEntity> result = underTest.findAllByModel("notExistingModel");
		
		assertThat(result).isEmpty();
	}
	
}
