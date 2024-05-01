package com.rafatars.parkingManager.services;

import java.util.Optional;

import com.rafatars.parkingManager.entities.mirrors.Company;

public interface ICompanyService extends IServices<Company> {
	Optional<Company> findByName(String name);
    Optional<Company> findByCnpj(String cnpj);
    Optional<Company> findByPhone(String phone);
}
