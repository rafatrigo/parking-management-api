package com.rafatars.parkingManager.entities;

import java.util.Collection;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vehicles")
public class VehicleOwnerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "vehicleOwner", cascade = CascadeType.ALL)
    private Set<VehicleEntity> vehicles;

    public void addVehicle(VehicleEntity vehicle)
    {
        this.vehicles.add(vehicle);
    }

    public void addVehicles(Collection<VehicleEntity> vehicles)
    {
        this.vehicles.addAll(vehicles);
    }

    public void removeVehicle(VehicleEntity vehicle)
    {
        this.vehicles.remove(vehicle);
    }

    public void removeVehicles(Collection<VehicleEntity> vehicles)
    {
        this.vehicles.removeAll(vehicles);
    }


}
