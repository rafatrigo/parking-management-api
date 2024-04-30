package com.rafatars.parkingManager.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rafatars.parkingManager.entities.CompanyEntity;
import com.rafatars.parkingManager.entities.mirrors.Company;
import com.rafatars.parkingManager.respositories.ICompanyRepository;
import com.rafatars.parkingManager.services.ICompanyService;

import com.rafatars.parkingManager.services.util.ServicesUtils;


@Service
public class CompanyService implements ICompanyService {
	
	private final ICompanyRepository companyRepository;
	
	@Autowired
	public CompanyService(ICompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public Company create(final Company obj) {
		
		final CompanyEntity compEntity = ServicesUtils.companyToCompanyEntity(obj);
		
		final CompanyEntity savedCompEntity = companyRepository.save(compEntity);
		
		return ServicesUtils.companyEntityToCompany(savedCompEntity);
	}

	@Override
	public Company update(Long id, final Company obj) {
		
		CompanyEntity updatingCompany = companyRepository.findById(id).get();
		
		
		updatingCompany.setName(obj.getName());
		updatingCompany.setCnpj(obj.getCnpj());
		updatingCompany.setAddress(obj.getAddress());
		updatingCompany.setPhone(obj.getPhone());
		
		CompanyEntity updatedCompany = companyRepository.save(updatingCompany);
		
		return ServicesUtils.companyEntityToCompany(updatedCompany);
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
		
		return foundCompanyEntity.map(company -> ServicesUtils.companyEntityToCompany(company));
	}

	@Override
	public List<Company> FindAll() {
		
		final Iterable<CompanyEntity> foundCompanyEntities = companyRepository.findAll();
		
		//convert from iterable to list
		final List<CompanyEntity> compList = StreamSupport.stream(foundCompanyEntities.spliterator(), false)
				.collect(Collectors.toList());
		
		//convert list elements from CompanyEntity to Company and returns the list
		return compList.stream()
				.map(compEntity -> ServicesUtils.companyEntityToCompany(compEntity))
				.collect(Collectors.toList());
	}

}
