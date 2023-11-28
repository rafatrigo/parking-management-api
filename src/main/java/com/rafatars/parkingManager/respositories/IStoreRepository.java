package com.rafatars.parkingManager.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.StoreEntity;

@Repository
public interface IStoreRepository extends CrudRepository<StoreEntity, Long>{
    
}
