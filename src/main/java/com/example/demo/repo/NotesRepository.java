package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Notes;



public interface NotesRepository extends JpaRepository<Notes,Integer>{
	
}
