package com.rafatars.parkingManager.respositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rafatars.parkingManager.entities.CompanyEntity;

@Repository
public interface ICompanyRepository extends CrudRepository<CompanyEntity, Long> {

}
