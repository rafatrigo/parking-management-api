package com.rafatars.parkingManager.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.RegisterEntity;

@Repository
public interface IRegisterRepository extends CrudRepository<RegisterEntity, Long>{
    
}
