package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Doubts;


public interface DoubtsRepository extends JpaRepository<Doubts,Integer>{
	
}
