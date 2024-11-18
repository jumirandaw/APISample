package br.atitus.edu.apisample.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.atitus.edu.apisample.entities.RegisterEntity;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, UUID>{

	
	
}
