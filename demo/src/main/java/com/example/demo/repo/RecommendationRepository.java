package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Recommendation;
import com.example.demo.entity.User;



public interface RecommendationRepository extends JpaRepository<Recommendation,Integer>{
	 List<Recommendation> findByUser(Optional<User> user);
	 void deleteByUser(User user);
}
