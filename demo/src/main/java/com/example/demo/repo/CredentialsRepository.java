package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User_Credentials;




public interface CredentialsRepository extends JpaRepository<User_Credentials,String> {
	
	Optional<User_Credentials> findByEmail(String email);

}
