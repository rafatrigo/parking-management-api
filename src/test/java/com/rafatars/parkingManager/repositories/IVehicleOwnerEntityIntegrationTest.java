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

import com.rafatars.parkingManager.TestDataUtils.TestDataUtilVehicle;
import com.rafatars.parkingManager.TestDataUtils.TestDataUtilVehicleOwner;
import com.rafatars.parkingManager.entities.VehicleEntity;
import com.rafatars.parkingManager.entities.VehicleOwnerEntity;
import com.rafatars.parkingManager.respositories.IVehicleOwnerRepository;

import jakarta.transaction.Transactional;;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class IVehicleOwnerEntityIntegrationTest {

    private IVehicleOwnerRepository underTest;

    @Autowired
    public IVehicleOwnerEntityIntegrationTest(IVehicleOwnerRepository underTest)
    {
        this.underTest = underTest;
    }
    

    @Test
    @Transactional //to avoid LazyInitializationException
    public void TestThatVehicleOwnerCanBeCreatedAndRecalled()
    {
        //create and save

        VehicleOwnerEntity owner = TestDataUtilVehicleOwner.createTestVehicleOwnerEntityA();

        underTest.save(owner);

        //---------------

        //test if it was created and can be recalled

        Optional<VehicleOwnerEntity> result = underTest.findById(owner.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(owner);
    }

    @Test
    @Transactional //to avoid LazyInitializationExeption
    public void TestThatMultiplesVehicleOwnersCanBeCreatedAndRecalled()
    {
        //create and save

        VehicleOwnerEntity owner1 = TestDataUtilVehicleOwner.createTestVehicleOwnerEntityA();
        VehicleOwnerEntity owner2 = TestDataUtilVehicleOwner.createTestVehicleOwnerEntityB();
        VehicleOwnerEntity owner3 = TestDataUtilVehicleOwner.createTestVehicleOwnerEntityC();

        underTest.save(owner1);
        underTest.save(owner2);
        underTest.save(owner3);

        //----------------

        //test if it was created and can be recalled

        Iterable<VehicleOwnerEntity> result = underTest.findAll();

        assertThat(result).hasSize(3).containsExactly(owner1, owner2, owner3);
    }

    @Test
    @Transactional //to avoid LazyInitializationExeption
    public void TestThatVehicleOwnerCanBeUpdated()
    {
        //create and save

        VehicleOwnerEntity owner = TestDataUtilVehicleOwner.createTestVehicleOwnerEntityA();

        underTest.save(owner);

        //---------------

        //update

        owner.setName("Updated");
        underTest.save(owner);

        //---------------

        //test if it was updated

        Optional<VehicleOwnerEntity> result = underTest.findById(owner.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(owner);
    }

    @Test
    @Transactional //to avoid LazyInitializationExeption
    public void TestThatVehicleOwnerCanBeDeleted()
    {
        //create and save

        VehicleOwnerEntity owner = TestDataUtilVehicleOwner.createTestVehicleOwnerEntityA();

        underTest.save(owner);

        //---------------

        //delete

        underTest.delete(owner);

        //---------------

        //test if it was deleted

        Optional<VehicleOwnerEntity> result = underTest.findById(owner.getId());

        assertThat(result).isEmpty();
    }

    @Test
    @Transactional //to avoid LazyInitializationExeption
    public void TestThatCanAddVehicleToVehicleOwner()
    {
        //create and save vehicle owner

        VehicleOwnerEntity owner = TestDataUtilVehicleOwner.createTestVehicleOwnerEntityA();

        underTest.save(owner);

        //---------------

        //create vehicle

        VehicleEntity vehicle = TestDataUtilVehicle.createTestVehicleEntityAWithoutOwner();

        //---------------

        //add vehicle to vehicle owner

        owner.addVehicle(vehicle);
        underTest.save(owner);

        //---------------

        //test if the vehicles was added correctly

        Optional<VehicleOwnerEntity> result = underTest.findById(owner.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(owner);

        //----------------
    }

    @Test
    @Transactional //to avoid LazyInitializationException
    public void TestThatVehicleCanBeRemovedFromVehicleOwner()
    {
        //create and save vehicle owner

        VehicleOwnerEntity owner = TestDataUtilVehicleOwner.createTestVehicleOwnerEntityA();

        underTest.save(owner);

        //---------------

        //create vehicle

        VehicleEntity vehicle = TestDataUtilVehicle.createTestVehicleEntityAWithoutOwner();

        //---------------

        //add vehicle to vehicle owner

        owner.addVehicle(vehicle);
        underTest.save(owner);

        //---------------

        //remove vehicle

        owner.removeVehicle(vehicle);
        underTest.save(owner);

        //---------------

        //test if the vehicle was removed

        VehicleOwnerEntity ownerWithoutVehicle = TestDataUtilVehicleOwner.createTestVehicleOwnerEntityA();

        Optional<VehicleOwnerEntity> result = underTest.findById(owner.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(ownerWithoutVehicle);

        //TODO fix this function, the vehicle is not been removed

    }

}
