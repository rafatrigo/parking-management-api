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

import com.rafatars.parkingManager.TestDataUtils.TestDataUtilCompany;
import com.rafatars.parkingManager.TestDataUtils.TestDataUtilParkingLot;
import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.entities.ParkingLotEntity;
import com.rafatars.parkingManager.respositories.ICompanyRepository;
import com.rafatars.parkingManager.respositories.IParkingLotRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class IParkingLotEntityIntegrationTest {
    
    private IParkingLotRepository underTest;
    private ICompanyRepository companyRepository;

    @Autowired
    public IParkingLotEntityIntegrationTest(IParkingLotRepository underTest, ICompanyRepository companyRepository) {
        this.underTest = underTest;
        this.companyRepository = companyRepository;
    }

    @Test
    @Transactional
    public void testThatParkingLotCanBeCreatedAndRecalled() {

        CompanyEntity company = TestDataUtilCompany.createTestCompanyEntityA();

        companyRepository.save(company);
        
        ParkingLotEntity parkingLot = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);

        underTest.save(parkingLot);
        
        Optional<ParkingLotEntity> result = underTest.findById(parkingLot.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(parkingLot);
    }

    @Test
    @Transactional
    public void testThatMultipleParkingLotsCanBeCreatedAndRecalled() {

        CompanyEntity company = TestDataUtilCompany.createTestCompanyEntityA();

        companyRepository.save(company);
        
        /*
         * change the ids because the parkinglots are being stored in a set,
         * so the id must be different otherwise the set will only store one parkinglot
         */
        ParkingLotEntity parkingLot1 = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);
        parkingLot1.setName("Parking Lot 1");
        ParkingLotEntity parkingLot2 = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);
        parkingLot2.setId(2L);
        parkingLot2.setName("Parking Lot 2");
        ParkingLotEntity parkingLot3 = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);
        parkingLot3.setId(3L);
        parkingLot3.setName("Parking Lot 3");

        underTest.save(parkingLot1);
        underTest.save(parkingLot2);
        underTest.save(parkingLot3);
        
        List<ParkingLotEntity> result = underTest.findAllByCompanyId(company.getId());

        assertThat(result).hasSize(3).containsExactly(parkingLot1, parkingLot2, parkingLot3);
    }

    @Test
    @Transactional
    public void testThatParkingLotCanBeUpdated() {

        CompanyEntity company = TestDataUtilCompany.createTestCompanyEntityA();

        companyRepository.save(company);
        
        ParkingLotEntity parkingLot = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);

        underTest.save(parkingLot);
        
        parkingLot.setName("updated");
        underTest.save(parkingLot);
        
        Optional<ParkingLotEntity> result = underTest.findById(parkingLot.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(parkingLot);
    }

    @Test
    public void testThatParkingLotCanBeDeleted() {
        CompanyEntity company = TestDataUtilCompany.createTestCompanyEntityA();

        companyRepository.save(company);
        
        ParkingLotEntity parkingLot = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);

        underTest.save(parkingLot);
        
        underTest.deleteById(parkingLot.getId());
        
        Optional<ParkingLotEntity> result = underTest.findById(parkingLot.getId());

        assertThat(result).isEmpty();
    }

    @Test
    @Transactional
    public void testThatCanFindParkingLotById() {
        CompanyEntity company = TestDataUtilCompany.createTestCompanyEntityA();

        companyRepository.save(company);
        
        ParkingLotEntity parkingLot = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);

        underTest.save(parkingLot);
        
        Optional<ParkingLotEntity> result = underTest.findById(parkingLot.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(parkingLot);
    }

    @Test
    @Transactional
    public void testThatIfCantFindByIdReturnsEmpty() {
        CompanyEntity company = TestDataUtilCompany.createTestCompanyEntityA();

        companyRepository.save(company);
        
        ParkingLotEntity parkingLot = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);

        underTest.save(parkingLot);
        
        Optional<ParkingLotEntity> result = underTest.findById(2L);

        assertThat(result).isEmpty();
    }

    @Test
    @Transactional
    public void testThatCanFindMultipleParkingLotsByCompany() {
        CompanyEntity company = TestDataUtilCompany.createTestCompanyEntityA();
        CompanyEntity companyB = TestDataUtilCompany.createTestCompanyEntityB();


        companyRepository.save(company);
        companyRepository.save(companyB);
        
        /*
         * change the ids because the parkinglots are being stored in a set,
         * so the id must be different otherwise the set will only store one parkinglot
         */
        ParkingLotEntity parkingLot1 = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);
        parkingLot1.setName("Parking Lot 1");
        ParkingLotEntity parkingLot2 = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(company);
        parkingLot2.setId(2L);
        parkingLot2.setName("Parking Lot 2");
        ParkingLotEntity parkingLot3 = TestDataUtilParkingLot.createTestParkingLotEntityToTestInCompany(companyB);
        parkingLot3.setId(3L);
        parkingLot3.setName("Parking Lot 3");

        underTest.save(parkingLot1);
        underTest.save(parkingLot2);
        underTest.save(parkingLot3);
        
        List<ParkingLotEntity> result = underTest.findAllByCompanyId(company.getId());

        assertThat(result).hasSize(2).containsExactly(parkingLot1, parkingLot2);
    }
    
}
