package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="reviews")
public class Reviews {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int Id;
		private String movieName;
		private String review;
		private int sentiment;
		private String rating;
		private String genre;
		
		public Reviews(int id, String movieName, String review, int sentiment, String rating, String genre, User user,
				List<Recommendation> recommendations) {
			super();
			Id = id;
			this.movieName = movieName;
			this.review = review;
			this.sentiment = sentiment;
			this.rating = rating;
			this.genre = genre;
			this.user = user;
			this.recommendations = recommendations;
		}

		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		private User user;
		
		@OneToMany(mappedBy="review",cascade=CascadeType.ALL, orphanRemoval= true)
		@JsonIgnore
		private List<Recommendation> recommendations;


		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getMovieName() {
			return movieName;
		}

		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}

		public String getReview() {
			return review;
		}

		public void setReview(String review) {
			this.review = review;
		}

		public int getSentiment() {
			return sentiment;
		}

		public void setSentiment(int sentiment) {
			this.sentiment = sentiment;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "Reviews [Id=" + Id + ", movieName=" + movieName + ", review=" + review + ", sentiment=" + sentiment
					+ ", rating=" + rating + ", genre=" + genre + ", user=" + user + ", recommendations="
					+ recommendations + "]";
		}

		public Reviews() {
			super();
			// TODO Auto-generated constructor stub
		}


		public List<Recommendation> getRecommendations() {
			return recommendations;
		}

		public void setRecommendations(List<Recommendation> recommendations) {
			this.recommendations = recommendations;
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

		
}