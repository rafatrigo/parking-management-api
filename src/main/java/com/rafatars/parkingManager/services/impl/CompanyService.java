package com.rafatars.parkingManager.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rafatars.parkingManager.dtos.CompanyDTO;
import com.rafatars.parkingManager.entities.CompanyEntity;
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
	public CompanyDTO create(final CompanyDTO obj) {
		
		final CompanyEntity compEntity = ServicesUtils.companyToCompanyEntity(obj);
		
		final CompanyEntity savedCompEntity = companyRepository.save(compEntity);
		
		return ServicesUtils.companyEntityToCompany(savedCompEntity);
	}

	@Override
	public CompanyDTO update(Long id, final CompanyDTO obj) {
		
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
	public Optional<CompanyDTO> findById(Long id) {
		
		final Optional<CompanyEntity> foundCompanyEntity = companyRepository.findById(id);
		
		return foundCompanyEntity.map(company -> ServicesUtils.companyEntityToCompany(company));
	}

	@Override
	public List<CompanyDTO> FindAll() {
		
		final Iterable<CompanyEntity> foundCompanyEntities = companyRepository.findAll();
		
		//convert from iterable to list
		final List<CompanyEntity> compList = StreamSupport.stream(foundCompanyEntities.spliterator(), false)
				.collect(Collectors.toList());
		
		//convert list elements from CompanyEntity to Company and returns the list
		return compList.stream()
				.map(compEntity -> ServicesUtils.companyEntityToCompany(compEntity))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<CompanyDTO> findByCnpj(String cnpj) {
		
		final Optional<CompanyEntity> foundCompanyEntity = companyRepository.findByCnpj(cnpj);
		
		return foundCompanyEntity.map(company -> ServicesUtils.companyEntityToCompany(company));
	}

	@Override
	public Optional<CompanyDTO> findByName(String name) {
		
		final Optional<CompanyEntity> foundCompanyEntity = companyRepository.findByName(name);
		
		return foundCompanyEntity.map(company -> ServicesUtils.companyEntityToCompany(company));
	}

	@Override
	public Optional<CompanyDTO> findByPhone(String phone) {
		
		final Optional<CompanyEntity> foundCompanyEntity = companyRepository.findByPhone(phone);
		
		return foundCompanyEntity.map(company -> ServicesUtils.companyEntityToCompany(company));
	}

}
