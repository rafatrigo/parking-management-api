package com.rafatars.parkingManager.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rafatars.parkingManager.TestDataUtils.TestDataUtilCompany;
import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.entities.mirrors.Company;
import com.rafatars.parkingManager.respositories.ICompanyRepository;
import com.rafatars.parkingManager.services.impl.CompanyService;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

	@Mock
	private ICompanyRepository companyRepository;
	
	@InjectMocks
	private CompanyService underTest;
	
	@Test
	public void testThatCompanyIsCreated() {
		
		final Company comp = TestDataUtilCompany.createTestCompanyA();
		
		final CompanyEntity compEntity = TestDataUtilCompany.createTestCompanyEntityA();
		
		when(companyRepository.save(eq(compEntity))).thenReturn(compEntity);
		
		final Company result = underTest.create(comp);
		
		assertEquals(result, comp);
	}
	 
	@Test
	public void testThatCompanyHasBeenDeleted() {
		final Long id = 1L;
		
		underTest.deleteById(id);
		verify(companyRepository, times(1)).deleteById(id);
	}
	
	@Test
	public void testThatCompanyHasBeenUpdated() {
		final Company oldCompany = TestDataUtilCompany.createTestCompanyA();
		
		Company newCompany = TestDataUtilCompany.createTestCompanyB();
		
		CompanyEntity saveMockReturn = underTest.companyToCompanyEntity(newCompany);
		
		saveMockReturn.setId(oldCompany.getId());
		
		
		when(companyRepository.findById(oldCompany.getId()))
			.thenReturn(Optional.of(underTest.companyToCompanyEntity(oldCompany)));
		
		when(companyRepository.save(saveMockReturn)).thenReturn(saveMockReturn);
		
		final Company updatedCompany = underTest.update(oldCompany.getId(), newCompany);
		
		newCompany.setId(oldCompany.getId());
		
		assertEquals(updatedCompany, newCompany);
		
	}
	
	@Test
	public void testThatCanFindCompanyById() {
		final Company company = TestDataUtilCompany.createTestCompanyA();
		
		when(companyRepository.findById(company.getId()))
			.thenReturn(Optional.of(underTest.companyToCompanyEntity(company)));
		
		final Optional<Company> foundCompany = underTest.findById(company.getId());
		
		assertThat(foundCompany.get()).isEqualTo(company);
	}
	
	@Test
	public void testThatIfCompanyNotFoundReturnsAnEmptyOptional() {
		final Long id = 1L;
		
		when(companyRepository.findById(id))
		.thenReturn(Optional.empty());
		
		final Optional<Company> result = underTest.findById(id);
		
		assertThat(result).isEmpty();
		
	}
	
}
