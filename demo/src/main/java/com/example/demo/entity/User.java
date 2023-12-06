package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user_details")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Reviews> reviews;
	

	public User(int id, String email, String password, List<Reviews> reviews, List<Recommendation> recommendations) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.reviews = reviews;

	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Reviews> getReviews() {
		return reviews;
	}
	
	public List<String> PositiveReviews(){
		return reviews.stream()
                .filter(review -> review.getSentiment() == 1)
                .map(Reviews::getMovieName)
                .collect(Collectors.toList());
	}

	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", reviews=" + reviews
				+ "]";
	}

	
	
	
//	public void updateMovie(int id,Reviews review) {
//		for(Reviews r:reviews) {
//			if(r.getId()==id) {
//				System.out.println(review.getMovie_name());
//				r.setMovie_name(review.getMovie_name());
//				r.setReview(review.getReview());
//				r.setSentiment(review.getSentiment());
//			}
//		}
//		
//		
//	}

}