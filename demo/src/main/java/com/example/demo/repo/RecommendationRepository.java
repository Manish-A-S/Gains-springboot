package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Recommendation;



public interface RecommendationRepository extends JpaRepository<Recommendation,Integer>{

}
