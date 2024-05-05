package com.rafatars.parkingManager.services;

import java.util.Optional;

import com.rafatars.parkingManager.entities.dtos.CompanyDTO;

public interface ICompanyService extends IServices<CompanyDTO> {
	Optional<CompanyDTO> findByName(String name);
    Optional<CompanyDTO> findByCnpj(String cnpj);
    Optional<CompanyDTO> findByPhone(String phone);
}
