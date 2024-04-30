package com.rafatars.parkingManager.respositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.CompanyEntity;

@Repository
public interface ICompanyRepository extends CrudRepository<CompanyEntity, Long> {
    
    Optional<CompanyEntity> findByName(String name);
    Optional<CompanyEntity> findByCnpj(String cnpj);
    Optional<CompanyEntity> findByPhone(String phone);
}
