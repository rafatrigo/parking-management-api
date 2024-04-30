// package com.rafatars.parkingManager.controller;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.annotation.DirtiesContext;
// import org.springframework.test.annotation.DirtiesContext.ClassMode;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.rafatars.parkingManager.TestDataUtil;
// import com.rafatars.parkingManager.entities.mirrors.Vehicle;
// import com.rafatars.parkingManager.services.impl.VehicleService;

// @SpringBootTest
// @ExtendWith(SpringExtension.class)
// @DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
// @AutoConfigureMockMvc
// public class VehicleControllerTest {
	
// 	private MockMvc mockMvc;
	
// 	private ObjectMapper objMapper;
	
// 	private VehicleService vehicleService;
	
// 	@Autowired
// 	public VehicleControllerTest(MockMvc mockMvc, ObjectMapper objMapper, VehicleService vehicleService) {
// 		this.mockMvc = mockMvc;
// 		this.objMapper = objMapper;
// 		this.vehicleService = vehicleService;
// 	}
	
// 	@Test
// 	public void testThatCreateVehicleReturnsHTTP201() throws Exception {
// 		final Vehicle vehicle = TestDataUtil.createTestVehicleWithoutCompanyA();
// 		final String vehicleJson = objMapper.writeValueAsString(vehicle);
		
// 		mockMvc.perform(MockMvcRequestBuilders.post("/vehicles")
// 				.contentType(MediaType.APPLICATION_JSON).content(vehicleJson))
// 			.andExpect(MockMvcResultMatchers.status().isCreated());
// 	}
	
// 	@Test
// 	public void testThatCreateVehicleReturnsCreatedVehicle() throws Exception {
// 		Vehicle vehicle = TestDataUtil.createTestVehicleWithoutCompanyA();
// 		vehicle.setId(null);
		
// 		final String vehicleJson = objMapper.writeValueAsString(vehicle);
		
// 		mockMvc.perform(MockMvcRequestBuilders.post("/vehicles")
// 				.contentType(MediaType.APPLICATION_JSON).content(vehicleJson))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(vehicle.getBrand()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.model").value(vehicle.getModel()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.color").value(vehicle.getColor()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.plate").value(vehicle.getPlate()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.type").value(vehicle.getType().toString()));
		
// 	}
	
	
// 	@Test
// 	public void testThatListVehiclesReturnsHTTP200() throws Exception {
// 		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles").contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(MockMvcResultMatchers.status().isOk());
// 	}
	
// 	@Test
// 	public void testThatListVehiclesReturnsVehicles() throws Exception {
// 		Vehicle vehicleA = TestDataUtil.createTestVehicleWithoutCompanyA();
// 		Vehicle vehicleB = TestDataUtil.createTestVehicleWithoutCompanyB();
		
// 		vehicleA.setId(null);
// 		vehicleB.setId(null);
		
// 		vehicleService.create(vehicleA);
// 		vehicleService.create(vehicleB);
		
// 		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles").contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[0].brand").value(vehicleA.getBrand()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value(vehicleA.getModel()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(vehicleA.getColor()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[0].plate").value(vehicleA.getPlate()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value(vehicleA.getType().toString()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNumber())
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[1].brand").value(vehicleB.getBrand()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[1].model").value(vehicleB.getModel()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[1].color").value(vehicleB.getColor()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[1].plate").value(vehicleB.getPlate()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value(vehicleB.getType().toString()));
		
// 	}
	
	
// 	@Test
// 	public void testThatGetVehicleByIdReturnsHTTP200WhenVehicleExist() throws Exception {
// 		Vehicle vehicle = TestDataUtil.createTestVehicleWithoutCompanyA();
		
// 		vehicle.setId(null);
		
// 		vehicleService.create(vehicle);
		
// 		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/1").contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(MockMvcResultMatchers.status().isOk());
		
// 	}
	
	
// 	@Test
// 	public void testTharGetVehicleByIdReturnsHTTP404WhenVehicleDoesNotExist() throws Exception {
// 		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/1").contentType(MediaType.APPLICATION_JSON))
// 		.andExpect(MockMvcResultMatchers.status().isNotFound());
// 	}
	
	
// 	@Test
// 	public void testThatGetVehicleByIdReturnsTheVehicleWhenTheVehicleExist() throws Exception {
// 		Vehicle vehicle = TestDataUtil.createTestVehicleWithoutCompanyA();
				
// 		vehicle.setId(null);
		
// 		vehicleService.create(vehicle);
		
// 		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/1").contentType(MediaType.APPLICATION_JSON))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(vehicle.getBrand()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.model").value(vehicle.getModel()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.color").value(vehicle.getColor()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.plate").value(vehicle.getPlate()))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.type").value(vehicle.getType().toString()));
// 	}
	
	
// 	@Test
// 	public void testThatUpdateVehicleReturnsHTTP404WhenVehicleDoesNotExist() throws Exception {
// 		final Vehicle vehicle = TestDataUtil.createTestVehicleWithoutCompanyA();
// 		final String vehicleJson = objMapper.writeValueAsString(vehicle);
		
// 		mockMvc.perform(MockMvcRequestBuilders.put("/vehicles/1")
// 				.contentType(MediaType.APPLICATION_JSON).content(vehicleJson))
// 			.andExpect(MockMvcResultMatchers.status().isNotFound());
// 	}
	
	
// 	@Test
// 	public void testThatUpdateVehicleReturnsHTTP200WhenVehicleIsUpdated() throws Exception {
// 		Vehicle vehicle = TestDataUtil.createTestVehicleWithoutCompanyA();
		
// 		vehicleService.create(vehicle);
		
// 		vehicle.setBrand("updatedBrand");
// 		final String vehicleJson = objMapper.writeValueAsString(vehicle);
		
// 		mockMvc.perform(MockMvcRequestBuilders.put("/vehicles/1")
// 				.contentType(MediaType.APPLICATION_JSON).content(vehicleJson))
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("updatedBrand"))
// 			.andExpect(MockMvcResultMatchers.status().isOk());
// 	}
	
	
// }
