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
	private String rating;
	private String genre;
	
	public Recommendation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recommendation(int id, String recommended_movies, String rating, String genre, Reviews review) {
		super();
		Id = id;
		this.recommended_movies = recommended_movies;
		this.rating = rating;
		this.genre = genre;
		this.review = review;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Reviews review;

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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Reviews getReview() {
		return review;
	}

	public void setReview(Reviews review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Recommendation [Id=" + Id + ", recommended_movies=" + recommended_movies + ", rating=" + rating
				+ ", genre=" + genre + ", review=" + review + "]";
	}

	
 

}