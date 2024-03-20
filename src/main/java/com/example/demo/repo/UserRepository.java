package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByEmail(String email);
	
	@Transactional
    @Modifying
    @Query("UPDATE User e SET e.dob = ?2, e.firstName = ?3 ,e.lastName = ?4,e.country = ?5,e.image = ?6,e.phone= ?7 WHERE e.id = ?1")
    int updateUser(int id,String dob, String firstName, String lastName,String country,String image,long phone);
	
	
	@Transactional
    @Modifying
    @Query("UPDATE User e SET e.grade = ?2, e.institute = ?3  WHERE e.id = ?1")
    int updateInstitute(int id,String grade,String institute);
}


