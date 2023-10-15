package com.rafatars.parkingManager.services;

import java.util.List;
import java.util.Optional;

public interface IServices<T> {
	
	T create(T obj);
	
	T update(Long id, T obj);
	
	void deleteById(Long id);
	
	Optional<T> findById(Long id);
	
	List<T> FindAll();
	
	
	
}
