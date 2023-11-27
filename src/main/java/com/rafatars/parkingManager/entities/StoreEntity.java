package com.rafatars.parkingManager.entities;

import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
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
@Table(name = "stores")
public class StoreEntity {
    
    // TODO make it an embedded id using the parkingLot and the arrival time.
    private Long id;
    private ZonedDateTime arrival;
    private ZonedDateTime departure;

    private ParkingLotEntity parkingLot;
    private VehicleEntity vehicle;

}
