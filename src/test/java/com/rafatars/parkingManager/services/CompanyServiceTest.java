package com.rafatars.parkingManager.services;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rafatars.parkingManager.TestDataUtils.TestDataUtilCompany;
import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.entities.dtos.CompanyDTO;
import com.rafatars.parkingManager.respositories.ICompanyRepository;
import com.rafatars.parkingManager.services.impl.CompanyService;
import com.rafatars.parkingManager.services.util.ServicesUtils;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

	@Mock
	private ICompanyRepository companyRepository;
	
	@InjectMocks
	private CompanyService underTest;
	
	@Test
	public void testThatCompanyIsCreated() {
		
		final CompanyDTO comp = TestDataUtilCompany.createTestCompanyA();
		
		final CompanyEntity compEntity = TestDataUtilCompany.createTestCompanyEntityA();
		
		when(companyRepository.save(eq(compEntity))).thenReturn(compEntity);
		
		final CompanyDTO result = underTest.create(comp);
		
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
		final CompanyDTO oldCompany = TestDataUtilCompany.createTestCompanyA();
		
		CompanyDTO newCompany = TestDataUtilCompany.createTestCompanyB();
		
		CompanyEntity saveMockReturn = ServicesUtils.companyToCompanyEntity(newCompany);
		
		saveMockReturn.setId(oldCompany.getId());
		
		
		when(companyRepository.findById(oldCompany.getId()))
			.thenReturn(Optional.of(ServicesUtils.companyToCompanyEntity(oldCompany)));
		
		when(companyRepository.save(saveMockReturn)).thenReturn(saveMockReturn);
		
		final CompanyDTO updatedCompany = underTest.update(oldCompany.getId(), newCompany);
		
		newCompany.setId(oldCompany.getId());
		
		assertEquals(updatedCompany, newCompany);
		
	}
	
	@Test
	public void testThatCanFindCompanyById() {
		final CompanyDTO company = TestDataUtilCompany.createTestCompanyA();
		
		when(companyRepository.findById(company.getId()))
			.thenReturn(Optional.of(ServicesUtils.companyToCompanyEntity(company)));
		
		final Optional<CompanyDTO> foundCompany = underTest.findById(company.getId());
		
		assertThat(foundCompany.get()).isEqualTo(company);
	}
	
	@Test
	public void testThatIfCompanyNotFoundReturnsAnEmptyOptional() {
		final Long id = 1L;
		
		when(companyRepository.findById(id))
		.thenReturn(Optional.empty());
		
		final Optional<CompanyDTO> result = underTest.findById(id);
		
		assertThat(result).isEmpty();
		
	}

	@Test
	public void testThatCanFindCompanyByName() {
		final CompanyDTO company = TestDataUtilCompany.createTestCompanyA();
		
		when(companyRepository.findByName(company.getName()))
			.thenReturn(Optional.of(ServicesUtils.companyToCompanyEntity(company)));
		
		final Optional<CompanyDTO> foundCompany = underTest.findByName(company.getName());
		
		assertThat(foundCompany.get()).isEqualTo(company);
	}

	@Test
	public void testThatIfCompanyNotFoundByNameReturnsAnEmptyOptional() {
		final String name = "Company A";
		
		when(companyRepository.findByName(name))
		.thenReturn(Optional.empty());
		
		final Optional<CompanyDTO> result = underTest.findByName(name);
		
		assertThat(result).isEmpty();
		
	}

	@Test
	public void testThatCanFindCompanyByCnpj() {
		final CompanyDTO company = TestDataUtilCompany.createTestCompanyA();
		
		when(companyRepository.findByCnpj(company.getCnpj()))
			.thenReturn(Optional.of(ServicesUtils.companyToCompanyEntity(company)));
		
		final Optional<CompanyDTO> foundCompany = underTest.findByCnpj(company.getCnpj());
		
		assertThat(foundCompany.get()).isEqualTo(company);
	}

	@Test
	public void testThatIfCompanyNotFoundByCnpjReturnsAnEmptyOptional() {
		final String cnpj = "12345678901234";
		
		when(companyRepository.findByCnpj(cnpj))
		.thenReturn(Optional.empty());

		final Optional<CompanyDTO> result = underTest.findByCnpj(cnpj);

		assertThat(result).isEmpty();

	}

	@Test
	public void testThatCanFindCompanyByPhone(){

		final CompanyDTO company = TestDataUtilCompany.createTestCompanyA();

		when(companyRepository.findByPhone(company.getPhone()))
			.thenReturn(Optional.of(ServicesUtils.companyToCompanyEntity(company)));

		final Optional<CompanyDTO> foundCompany = underTest.findByPhone(company.getPhone());

		assertThat(foundCompany.get()).isEqualTo(company);
		
	}

	@Test
	public void testThatIfCompanyNotFoundByPhoneReturnsAnEmptyOptional() {
		final String phone = "1234567890";
		
		when(companyRepository.findByPhone(phone))
		.thenReturn(Optional.empty());

		final Optional<CompanyDTO> result = underTest.findByPhone(phone);

		assertThat(result).isEmpty();

	}
	
}
