package com.rafatars.parkingManager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rafatars.parkingManager.dtos.CompanyDTO;
import com.rafatars.parkingManager.services.ICompanyService;

@RestController
public class CompanyController {

	private ICompanyService companyService;
	
	@Autowired
	public CompanyController(ICompanyService companyService) {
		this.companyService = companyService;
	}
	
	/**
	 * Get all companies
	 * 
	 * @return A list of companies or a empty list
	 */
	@GetMapping(path = "/companies")
	public List<CompanyDTO> getCompanies() {
		
		List<CompanyDTO> companies = companyService.FindAll();
		
		return companies;
	}
	
	/**
	 * Find a company by her id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/companies/{id}")
	public ResponseEntity<CompanyDTO> getCompanyById(
			@PathVariable("id") Long id
			){
		
		Optional<CompanyDTO> foundCompany = companyService.findById(id);
		
		if(foundCompany.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundCompany.get(), HttpStatus.OK); 
		}
	
	}

	@GetMapping(path = "/companies", params = "name")
	public ResponseEntity<CompanyDTO> getCompanyByName(
			@Param("name") String name
			){
		
		Optional<CompanyDTO> foundCompany = companyService.findByName(name);
		
		if(foundCompany.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundCompany.get(), HttpStatus.OK); 
		}
	
	}

	@GetMapping(path = "/companies", params = "cnpj")
	public ResponseEntity<CompanyDTO> getCompanyByCnpj(
			@Param("cnpj") String cnpj
			){
		
		Optional<CompanyDTO> foundCompany = companyService.findByCnpj(cnpj);
		
		if(foundCompany.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(foundCompany.get(), HttpStatus.OK); 
		}
	
	}

	@GetMapping(path = "/companies", params = "phone")
	public ResponseEntity<CompanyDTO> getCompanyByPhone(
			@Param("phone") String phone
			){
		
		Optional<CompanyDTO> foundCompany = companyService.findByPhone(phone);
		
		if(foundCompany.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<>(foundCompany.get(), HttpStatus.OK);
		}
	
	}
	
	/**
	 * Create a company
	 * 
	 * @param company
	 * @return
	 */
	@PostMapping(path = "/companies")
	public ResponseEntity<CompanyDTO> createCompany(
			@RequestBody final CompanyDTO company
			){
		
		CompanyDTO createdCompany = companyService.create(company);
	
		return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
	}
	
	/**
	 * Update company
	 * 
	 * @param id
	 * @param company
	 * @return
	 */
	@PutMapping(path = "/companies/{id}")
	public ResponseEntity<CompanyDTO> updateCompany(
			@PathVariable("id") Long id, 
			@RequestBody final CompanyDTO company)
	{
		
		Optional<CompanyDTO> edtCompany = companyService.findById(id);
		
		if(edtCompany.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			CompanyDTO updatedCompany = companyService.update(id, company);
			
			return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
		}
		
	}
	
	/**
	 * Delete a company
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = "/companies/{id}")
	public <T> ResponseEntity<T> delteCompany(@PathVariable("id") Long id) {
		
		final Optional<CompanyDTO> company = companyService.findById(id);
		
		if(company.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			companyService.deleteById(id);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
}
