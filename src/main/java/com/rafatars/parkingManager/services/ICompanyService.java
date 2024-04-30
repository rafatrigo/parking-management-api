package com.rafatars.parkingManager.services;

import com.rafatars.parkingManager.entities.mirrors.Company;

public interface ICompanyService extends IServices<Company> {
	Company findByName(String name);
    Company findByCnpj(String cnpj);
    Company findByPhone(String phone);
}
