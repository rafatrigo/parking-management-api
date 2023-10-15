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
import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.respositories.ICompanyRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ICompanyEntityIntegrationTest {

	private ICompanyRepository underTest;

	@Autowired
	public ICompanyEntityIntegrationTest(ICompanyRepository underTest) {
		this.underTest = underTest;
	}
	
	@Test
	public void testThatCompanyCanBeCreatedAndRecalled() {
		
		CompanyEntity comp = TestDataUtil.createTestCompanyEntityA();
		
		underTest.save(comp);
		
		Optional<CompanyEntity> result = underTest.findById(comp.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(comp);
	}

	
	@Test
	public void testThatMultipleCompaniesCanBeCreatedAndRecaled() {
		
		CompanyEntity comp1 = TestDataUtil.createTestCompanyEntityA();
		underTest.save(comp1);
		CompanyEntity comp2 = TestDataUtil.createTestCompanyEntityB();
		underTest.save(comp2);
		CompanyEntity comp3 = TestDataUtil.createTestCompanyEntityC();
		underTest.save(comp3);
		
		
		
		Iterable<CompanyEntity> result = underTest.findAll();
		
		assertThat(result).hasSize(3).containsExactly(comp1, comp2, comp3);
		
	}
	
	
	@Test
	public void testThatCompanyCanBeUpdated() {
		
		CompanyEntity comp = TestDataUtil.createTestCompanyEntityA();
		underTest.save(comp);
		
		comp.setName("updated");
		underTest.save(comp);
		
		Optional<CompanyEntity> result = underTest.findById(comp.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(comp);
		
	}
	
	@Test
	public void testThatCompanyCanBeDeleted() {
		CompanyEntity comp = TestDataUtil.createTestCompanyEntityA();
		underTest.save(comp);
		
		underTest.deleteById(comp.getId());
		
		Optional<CompanyEntity> result = underTest.findById(comp.getId());
		
		
		assertThat(result).isEmpty();
	}
	
	
	
}
