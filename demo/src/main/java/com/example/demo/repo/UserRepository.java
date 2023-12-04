package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByEmail(String email);
	
	@Query("Select id from User")
    List<Integer> getIds();
}
