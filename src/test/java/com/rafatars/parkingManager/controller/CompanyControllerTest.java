package com.rafatars.parkingManager.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.rafatars.parkingManager.TestDataUtils.TestDataUtilCompany;
import com.rafatars.parkingManager.entities.dtos.CompanyDTO;
import com.rafatars.parkingManager.services.impl.CompanyService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CompanyControllerTest {
	
	private MockMvc mockMvc;

	private ObjectMapper objMapper;
	
	private CompanyService companyService;
	
	
	@Autowired
	public CompanyControllerTest(MockMvc mockMvc, ObjectMapper objMapper, CompanyService companyService) {
		this.mockMvc = mockMvc;
		this.objMapper = objMapper;
		this.companyService = companyService;
	}



	@Test
	public void testThatCreateCompanyReturnsHTTP201() throws Exception {
		final CompanyDTO company = TestDataUtilCompany.createTestCompanyA();
		final String companyJson = objMapper.writeValueAsString(company);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/companies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(companyJson))
				.andExpect(MockMvcResultMatchers.status().isCreated());
				
	}
	
	@Test 
	public void testThatCreateCompanyReturnsCreatedCompany() throws Exception {
		CompanyDTO company = TestDataUtilCompany.createTestCompanyA();
		
		company.setId(null);
		
		final String companyJson = objMapper.writeValueAsString(company);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/companies")
				.contentType(MediaType.APPLICATION_JSON).content(companyJson))
			
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(company.getName()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.address").value(company.getAddress()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(company.getPhone()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.cnpj").value(company.getCnpj()));
		
	}
	
	@Test
	public void testThatListCompaniesReturnsHTTP200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/companies")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatListCompaniesReturnsTheCompanies() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		CompanyDTO compB = TestDataUtilCompany.createTestCompanyB();
		
		compA.setId(null);
		compB.setId(null);
		
		companyService.create(compA);
		companyService.create(compB);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(compA.getName()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNumber())
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(compB.getName()));
	}
	
	@Test
	public void testThatGetCompanyByIdReturnsHTTP200WhenCompanyExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testThatGetCompanyByIdReturnsCompanyWhenCompanyExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(compA.getName()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.address").value(compA.getAddress()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(compA.getPhone()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.cnpj").value(compA.getCnpj()));
		
	}
	
	@Test
	public void testThatGetCompanyByIdReturnsHTTP404WhenCompanyDoesNotExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies/55")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		
		
	}

	@Test
	public void testThatGetCompanyByCnpjReturnsHTTP200WhenCompanyExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);

		String cnpj = compA.getCnpj();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies?cnpj=" + cnpj)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

	@Test
	public void testThatGetCompanyByCnpjReturnsCompanyWhenCompanyExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);

		String cnpj = compA.getCnpj();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies?cnpj=" + cnpj)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(compA.getName()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.address").value(compA.getAddress()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(compA.getPhone()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.cnpj").value(compA.getCnpj()));
		
	}

	@Test
	public void testThatGetCompanyByCnpjReturnsHTTP404WhenCompanyDoesNotExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);

		String cnpj = "00000";
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies?cnpj=" + cnpj)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		
		
	}

	@Test
	public void testThatGetCompanyByPhoneReturnsHTTP200WhenCompanyExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);

		String phone = compA.getPhone();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies?phone=" + phone)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

	@Test
	public void testThatGetCompanyByPhoneReturnsCompanyWhenCompanyExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);

		String phone = compA.getPhone();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies?phone=" + phone)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(compA.getName()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.address").value(compA.getAddress()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(compA.getPhone()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.cnpj").value(compA.getCnpj()));
		
	}

	@Test
	public void testThatGetCompanyByPhoneReturnsHTTP404WhenCompanyDoesNotExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);

		String phone = "123";
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies?phone=" + phone)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}

	@Test
	public void testThatGetCompanyByNameReturnsHTTP200WhenCompanyExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);

		String name = compA.getName();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies?name=" + name)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

	@Test
	public void testThatGetCompanyByNameReturnsCompanyWhenCompanyExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);

		String name = compA.getName();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies?name=" + name)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(compA.getName()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.address").value(compA.getAddress()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(compA.getPhone()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.cnpj").value(compA.getCnpj()));
		
	}

	@Test
	public void testThatGetCompanyByNameReturnsHTTP404WhenCompanyDoesNotExist() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		compA.setId(null);
		companyService.create(compA);

		String name = "name";
		
		mockMvc.perform(MockMvcRequestBuilders.get("/companies?name=" + name)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	
	@Test
	public void testThatUpdateCompanyReturnsHTTP404WhenCompanyDoesNotExist() throws Exception {
		final CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		final String compAJson = objMapper.writeValueAsString(compA);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/companies/55")
				.contentType(MediaType.APPLICATION_JSON).content(compAJson))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		
		
	}
	
	
	@Test
	public void testThatUpdateCompanyReturnsHTTP200WhenCompanyIsUpdated() throws Exception {
		CompanyDTO compA = TestDataUtilCompany.createTestCompanyA();
		companyService.create(compA);
		
		compA.setName("updatedName");
		
		final String compAJson = objMapper.writeValueAsString(compA);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/companies/1")
				.contentType(MediaType.APPLICATION_JSON).content(compAJson))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("updatedName"))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
}
