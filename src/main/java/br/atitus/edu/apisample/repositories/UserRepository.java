package br.atitus.edu.apisample.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.atitus.edu.apisample.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
	
	//Query Method 
	boolean existsByEmail(String email);

}
