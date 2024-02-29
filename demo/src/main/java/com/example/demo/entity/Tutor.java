package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.Arrays;


import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;


@Entity
@Table(name="tutor")
public class Tutor {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String topic;
		private String email;
		private String content;
		@Lob
		private byte[] video;
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


		public String getTopic() {
			return topic;
		}


		public void setTopic(String topic) {
			this.topic = topic;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getContent() {
			return content;
		}


		public void setContent(String content) {
			this.content = content;
		}


		public byte[] getVideo() {
			return video;
		}


		public void setVideo(byte[] video) {
			this.video = video;
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


		@Override
		public String toString() {
			return "Tutor [id=" + id + ", topic=" + topic + ", email=" + email + ", content=" + content + ", video="
					+ Arrays.toString(video) + ", timestamp=" + timestamp + ", topics=" + topics + "]";
		}


		public Tutor(int id, String topic, String email, String content, byte[] video, Timestamp timestamp,
				Topic topics) {
			super();
			this.id = id;
			this.topic = topic;
			this.email = email;
			this.content = content;
			this.video = video;
			this.timestamp = timestamp;
			this.topics = topics;
		}


		public Tutor() {
			super();
			// TODO Auto-generated constructor stub
		}
		

		
		
}