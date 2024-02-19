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
		private String topic;
		private String content;
		private String question;
		private String answer;
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

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
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
			return "Doubts [id=" + id + ", topic=" + topic + ", content=" + content + ", question=" + question
					+ ", answer=" + answer + ", timestamp=" + timestamp + ", topics=" + topics + "]";
		}

		public Doubts(int id, String topic, String content, String question, String answer, Timestamp timestamp,
				Topic topics) {
			super();
			this.id = id;
			this.topic = topic;
			this.content = content;
			this.question = question;
			this.answer = answer;
			this.timestamp = timestamp;
			this.topics = topics;
		}

		public Doubts() {
			super();
			// TODO Auto-generated constructor stub
		}
		


}