package com.rafatars.parkingManager.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rafatars.parkingManager.entities.Company;
import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.respositories.ICompanyRepository;
import com.rafatars.parkingManager.services.ICompanyService;


@Service
public class CompanyService implements ICompanyService {
	
	private final ICompanyRepository companyRepository;
	
	@Autowired
	public CompanyService(ICompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public Company create(final Company obj) {
		
		final CompanyEntity compEntity = companyToCompanyEntity(obj);
		
		final CompanyEntity savedCompEntity = companyRepository.save(compEntity);
		
		return companyEntityToCompany(savedCompEntity);
	}

	@Override
	public Company update(Long id, final Company obj) {
		
		CompanyEntity updatingCompany = companyRepository.findById(id).get();
		
		
		updatingCompany.setName(obj.getName());
		updatingCompany.setCnpj(obj.getCnpj());
		updatingCompany.setAddress(obj.getAddress());
		updatingCompany.setPhone(obj.getPhone());
		updatingCompany.setMotorcycleParkingSpaces(obj.getMotorcycleParkingSpaces());
		updatingCompany.setCarParkingSpaces(obj.getCarParkingSpaces());
		
		CompanyEntity updatedCompany = companyRepository.save(updatingCompany);
		
		return companyEntityToCompany(updatedCompany);
	}
	
	@Override
	public void deleteById(Long id) {
		
		try {
			companyRepository.deleteById(id);

	    } catch (final EmptyResultDataAccessException ex) {
	     
	    }
		
		
	}

	@Override
	public Optional<Company> findById(Long id) {
		
		final Optional<CompanyEntity> foundCompanyEntity = companyRepository.findById(id);
		
		return foundCompanyEntity.map(company -> companyEntityToCompany(company));
	}

	@Override
	public List<Company> FindAll() {
		
		final Iterable<CompanyEntity> foundCompanyEntities = companyRepository.findAll();
		
		//convert from iterable to list
		final List<CompanyEntity> compList = StreamSupport.stream(foundCompanyEntities.spliterator(), false)
				.collect(Collectors.toList());
		
		//convert list elements from CompanyEntity to Company and returns the list
		return compList.stream()
				.map(compEntity -> companyEntityToCompany(compEntity))
				.collect(Collectors.toList());
	}
	
	public CompanyEntity companyToCompanyEntity(Company comp) {
		return CompanyEntity.builder()
				.id(comp.getId())
				.name(comp.getName())
				.cnpj(comp.getCnpj())
				.address(comp.getAddress())
				.phone(comp.getPhone())
				.motorcycleParkingSpaces(comp.getMotorcycleParkingSpaces())
				.carParkingSpaces(comp.getCarParkingSpaces())
				.build();
	}
	
	public Company companyEntityToCompany(CompanyEntity comp) {
		return Company.builder()
				.id(comp.getId())
				.name(comp.getName())
				.cnpj(comp.getCnpj())
				.address(comp.getAddress())
				.phone(comp.getPhone())
				.motorcycleParkingSpaces(comp.getMotorcycleParkingSpaces())
				.carParkingSpaces(comp.getCarParkingSpaces())
				.build();
	}

}
