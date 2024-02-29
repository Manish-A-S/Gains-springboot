package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Topic;



public interface TopicRepository extends JpaRepository<Topic,Integer>{
	
}
