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

import com.rafatars.parkingManager.TestDataUtils.TestDataUtilVehicle;
import com.rafatars.parkingManager.dtos.VehicleDTO;
import com.rafatars.parkingManager.services.impl.VehicleService;
import com.rafatars.parkingManager.services.util.ServicesUtils;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class VehicleControllerTest {
	
	private MockMvc mockMvc;
	
	private ObjectMapper objMapper;
	
	private VehicleService vehicleService;
	
	@Autowired
	public VehicleControllerTest(MockMvc mockMvc, ObjectMapper objMapper, VehicleService vehicleService) {
		this.mockMvc = mockMvc;
		this.objMapper = objMapper;
		this.vehicleService = vehicleService;
	}
	
	@Test
	public void testThatCreateVehicleReturnsHTTP201() throws Exception {
		final VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
		final String vehicleJson = objMapper.writeValueAsString(vehicle);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/vehicles")
				.contentType(MediaType.APPLICATION_JSON).content(vehicleJson))
			.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testThatCreateVehicleReturnsCreatedVehicle() throws Exception {
		VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
		vehicle.setId(null);
		
		final String vehicleJson = objMapper.writeValueAsString(vehicle);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/vehicles")
				.contentType(MediaType.APPLICATION_JSON).content(vehicleJson))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
			.andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(vehicle.getBrand()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.model").value(vehicle.getModel()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.color").value(vehicle.getColor()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.plate").value(vehicle.getPlate()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.type").value(vehicle.getType().toString()));
		
	}

    @Test
    public void testThatDeleteVehicleReturnsHTTP204WhenVehicleIsDeleted() throws Exception {
        VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        
        vehicle.setId(null);
        
        vehicleService.create(vehicle);
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/vehicles/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteVehicleReturnsHTTP404WhenVehicleDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/vehicles/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
	
	
	@Test
	public void testThatListVehiclesReturnsHTTP200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatListVehiclesReturnsVehicles() throws Exception {
		VehicleDTO vehicleA = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
		VehicleDTO vehicleB = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityB());
		
		vehicleA.setId(null);
		vehicleB.setId(null);
		
		vehicleService.create(vehicleA);
		vehicleService.create(vehicleB);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].brand").value(vehicleA.getBrand()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value(vehicleA.getModel()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(vehicleA.getColor()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].plate").value(vehicleA.getPlate()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value(vehicleA.getType().toString()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNumber())
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].brand").value(vehicleB.getBrand()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].model").value(vehicleB.getModel()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].color").value(vehicleB.getColor()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].plate").value(vehicleB.getPlate()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value(vehicleB.getType().toString()));
		
	}
	
	
	@Test
	public void testThatGetVehicleByIdReturnsHTTP200WhenVehicleExist() throws Exception {
		VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
		
		vehicle.setId(null);
		
		vehicleService.create(vehicle);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testTharGetVehicleByIdReturnsHTTP404WhenVehicleDoesNotExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	
	@Test
	public void testThatGetVehicleByIdReturnsTheVehicleWhenTheVehicleExist() throws Exception {
		VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
				
		vehicle.setId(null);
		
		vehicleService.create(vehicle);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
			.andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(vehicle.getBrand()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.model").value(vehicle.getModel()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.color").value(vehicle.getColor()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.plate").value(vehicle.getPlate()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.type").value(vehicle.getType().toString()));
	}
	
	
	@Test
	public void testThatUpdateVehicleReturnsHTTP404WhenVehicleDoesNotExist() throws Exception {
		final VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityB());
        
        final String vehicleJson = objMapper.writeValueAsString(vehicle);
        
        vehicle.setId(null);
        vehicleService.create(vehicle);
        
		mockMvc.perform(MockMvcRequestBuilders.put("/vehicles/55")
				.contentType(MediaType.APPLICATION_JSON).content(vehicleJson))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	
	@Test
	public void testThatUpdateVehicleReturnsHTTP200WhenVehicleIsUpdated() throws Exception {
		VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
		
		vehicleService.create(vehicle);
		
		vehicle.setBrand("updatedBrand");
		final String vehicleJson = objMapper.writeValueAsString(vehicle);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/vehicles/1")
				.contentType(MediaType.APPLICATION_JSON).content(vehicleJson))
			.andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("updatedBrand"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
    @Test
    public void testThatGetVehicleByPlateReturnsHTTP200WhenVehicleExist() throws Exception {
        VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        
        vehicle.setId(null);
        
        vehicleService.create(vehicle);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?plate=" + vehicle.getPlate())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void testThatGetVehicleByPlateReturnsTheVehicleWhenTheVehicleExist() throws Exception {
        VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        
        vehicle.setId(null);
        
        vehicleService.create(vehicle);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?plate=" + vehicle.getPlate())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
            .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(vehicle.getBrand()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(vehicle.getModel()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(vehicle.getColor()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.plate").value(vehicle.getPlate()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.type").value(vehicle.getType().toString()));
    }

    @Test
    public void testThatGetVehicleByPlateReurnsHTTP404WhenVehicleDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?plate=123456")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetVehicleByTypeReturnsHTTP200WhenVehicleExist() throws Exception {
        VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        
        vehicle.setId(null);
        
        vehicleService.create(vehicle);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?type=" + vehicle.getType())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void testThatGetVehiclesByTypeReturnsTheVehiclesWhenTheyExist() throws Exception {
        VehicleDTO vehicle1 = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        VehicleDTO vehicle2 = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityB());
        
        vehicle1.setId(null);
        vehicle2.setId(null);

        vehicleService.create(vehicle1);
        vehicleService.create(vehicle2);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?type=" + vehicle1.getType())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand").value(vehicle1.getBrand()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value(vehicle1.getModel()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(vehicle1.getColor()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].plate").value(vehicle1.getPlate()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value(vehicle1.getType().toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNumber())
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].brand").value(vehicle2.getBrand()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].model").value(vehicle2.getModel()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].color").value(vehicle2.getColor()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].plate").value(vehicle2.getPlate()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value(vehicle2.getType().toString()));
    }

    @Test
    public void testThatGetVehiclesByTypeReturnsHTTP404WhenVehiclesDoNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?type=CAR")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetVehiclesByColorReturnsHTTP200WhenVehiclesExist() throws Exception {
        VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        
        vehicle.setId(null);
        
        vehicleService.create(vehicle);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?color=" + vehicle.getColor())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void testThatGetVehiclesByColorReturnsTheVehiclesWhenTheyExist() throws Exception {
        VehicleDTO vehicle1 = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        VehicleDTO vehicle2 = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityB());
        
        vehicle1.setId(null);
        vehicle2.setId(null);

        vehicleService.create(vehicle1);
        vehicleService.create(vehicle2);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?color=" + vehicle2.getColor())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand").value(vehicle2.getBrand()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value(vehicle2.getModel()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(vehicle2.getColor()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].plate").value(vehicle2.getPlate()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value(vehicle2.getType().toString()));
    }

    @Test
    public void testThatGetVehiclesByColorReturnsHTTP404WhenVehiclesDoNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?color=RED")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetVehiclesByBrandReturnsHTTP200WhenVehiclesExist() throws Exception {
        VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        
        vehicle.setId(null);
        
        vehicleService.create(vehicle);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?brand=" + vehicle.getBrand())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void testThatGetVehiclesByBrandReturnsTheVehiclesWhenTheyExist() throws Exception {
        VehicleDTO vehicle1 = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        VehicleDTO vehicle2 = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityB());
        
        vehicle1.setId(null);
        vehicle2.setId(null);

        vehicleService.create(vehicle1);
        vehicleService.create(vehicle2);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?brand=" + vehicle1.getBrand())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand").value(vehicle1.getBrand()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value(vehicle1.getModel()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(vehicle1.getColor()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].plate").value(vehicle1.getPlate()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value(vehicle1.getType().toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").isNumber())
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].brand").value(vehicle2.getBrand()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].model").value(vehicle2.getModel()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].color").value(vehicle2.getColor()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].plate").value(vehicle2.getPlate()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value(vehicle2.getType().toString()));
    }

    @Test
    public void testThatGetVehiclesByBrandReturnsHTTP404WhenVehiclesDoNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?brand=TOYOTA")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetVehiclesByModelReturnsHTTP200WhenVehiclesExist() throws Exception {
        VehicleDTO vehicle = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        
        vehicle.setId(null);
        
        vehicleService.create(vehicle);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?model=" + vehicle.getModel())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void testThatGetVehiclesByModelReturnsTheVehiclesWhenTheyExist() throws Exception {
        VehicleDTO vehicle1 = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityA());
        VehicleDTO vehicle2 = ServicesUtils.vehicleEntityToVehicle(TestDataUtilVehicle.createTestVehicleEntityB());
        
        vehicle1.setId(null);
        vehicle2.setId(null);

        vehicleService.create(vehicle1);
        vehicleService.create(vehicle2);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?model=" + vehicle2.getModel())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand").value(vehicle2.getBrand()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].model").value(vehicle2.getModel()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(vehicle2.getColor()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].plate").value(vehicle2.getPlate()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value(vehicle2.getType().toString()));
    }

    @Test
    public void testThatGetVehiclesByModelReturnsHTTP404WhenVehiclesDoNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles?model=MODEL3")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
	
}
