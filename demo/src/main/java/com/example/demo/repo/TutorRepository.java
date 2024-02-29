package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Tutor;


public interface TutorRepository extends JpaRepository<Tutor,Integer>{
	
}
