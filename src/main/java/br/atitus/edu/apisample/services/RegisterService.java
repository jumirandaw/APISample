package br.atitus.edu.apisample.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.atitus.edu.apisample.entities.RegisterEntity;
import br.atitus.edu.apisample.repositories.RegisterRepository;

@Service
public class RegisterService {

	private final RegisterRepository repository; 

	public RegisterService(RegisterRepository repository) {
		super();
		this.repository = repository;
	}
	
	// Save
	public RegisterEntity save(RegisterEntity newRegister) throws Exception {
		
		// Validações de regra de negócio 
		if (newRegister.getLatitude() < -90 || newRegister.getLatitude() > 90)
			throw new Exception("Latitude Inválida!");
		if (newRegister.getLongitude() < -180 || newRegister.getLongitude() > 180)
			throw new Exception("Longitude Inválida!");
		if (newRegister.getUser() == null || newRegister.getUser().getId() == null)
			throw new Exception("Usuário inválido!");
			
			
		//Invocar o método da camada repository
		repository.save(newRegister);
		
		
		
		return newRegister;
	}
	
	
	
	// FindById
	public RegisterEntity findById(UUID id) throws Exception {
		RegisterEntity entity = repository.findById(id)
				.orElseThrow(() -> new Exception("Usuário não encontrado com este id"));
		
		return entity;
		
	}
	
	
	// FindAll
	public List<RegisterEntity> findAll() throws Exception {
		List<RegisterEntity> registers = repository.findAll();
		
		return registers;
	}
	
	
	
	// Delete 
	public void deleteById(UUID id) throws Exception {
		repository.deleteById(id);
		
	}
}
