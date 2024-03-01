package com.example.demo.entity;

import java.sql.Timestamp;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
@Table(name="doubts")
public class Doubts {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String text;
		private String sender;
		private Timestamp timestamp;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		private Topic topics;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Doubts(int id, String text, String sender, Timestamp timestamp, Topic topics) {
			super();
			this.id = id;
			this.text = text;
			this.sender = sender;
			this.timestamp = timestamp;
			this.topics = topics;
		}

		@Override
		public String toString() {
			return "Doubts [id=" + id + ", text=" + text + ", sender=" + sender + ", timestamp=" + timestamp + ", topics="
					+ topics + "]";
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getSender() {
			return sender;
		}

		public void setSender(String sender) {
			this.sender = sender;
		}

		public Timestamp getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
		}

		public Topic getTopics() {
			return topics;
		}

		public void setTopics(Topic topics) {
			this.topics = topics;
		}

		public Doubts() {
			super();
			// TODO Auto-generated constructor stub
		}
		


}