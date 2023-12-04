package com.example.demo.repo;

import java.util.List;
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
    @Query("UPDATE Reviews e SET e.movieName = ?1, e.review = ?2 ,e.sentiment = ?3 WHERE e.id = ?4")
    int updateMovie(String movieName, String review, int sentiment,int id);
	
	Optional<Reviews> findReviewsBymovieName(String movie_name);
	
	@Query("SELECT movieName FROM Reviews")
    List<String> getAllMovieNames();
}
