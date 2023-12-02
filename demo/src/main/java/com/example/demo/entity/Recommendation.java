package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="recommendation")
public class Recommendation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String recommended_movies;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Recommendation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recommendation(int id, String recommended_movies, User user) {
		super();
		Id = id;
		this.recommended_movies = recommended_movies;
		this.user = user;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRecommended_movies() {
		return recommended_movies;
	}

	public void setRecommended_movies(String recommended_movies) {
		this.recommended_movies = recommended_movies;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Recommendation [Id=" + Id + ", recommended_movies=" + recommended_movies + ", user=" + user + "]";
	}
	

        
 

}
