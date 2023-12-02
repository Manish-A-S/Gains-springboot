package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Reviews;
import com.example.demo.entity.User;

import jakarta.transaction.Transactional;


public interface ReviewsRepository extends JpaRepository<Reviews,Integer>{
	
	@Transactional
    @Modifying
    @Query("UPDATE Reviews e SET e.movie_name = ?1, e.review = ?2 ,e.sentiment = ?3 WHERE e.id = ?4")
    int updateMovie(String movie_name, String review, int sentiment,int id);
	
}
