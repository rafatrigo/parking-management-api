package com.rafatars.parkingManager.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.UserEntity;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long>{
    
}
