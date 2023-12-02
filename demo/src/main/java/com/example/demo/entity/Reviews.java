package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name="reviews")
public class Reviews {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int Id;
		private String movie_name;
		private String review;
		private int sentiment;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		private User user;

		public Reviews() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Reviews(int id, String movie_name, String review, int sentiment, User user) {
			super();
			Id = id;
			this.movie_name = movie_name;
			this.review = review;
			this.sentiment = sentiment;
			this.user = user;
		}

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getMovie_name() {
			return movie_name;
		}

		public void setMovie_name(String movie_name) {
			this.movie_name = movie_name;
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
			return "Reviews [Id=" + Id + ", movie_name=" + movie_name + ", review=" + review + ", sentiment="
					+ sentiment + ", user=" + user + "]";
		}
}
