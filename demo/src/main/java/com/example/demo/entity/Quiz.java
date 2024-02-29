package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="quiz")
public class Quiz {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String topic;
		private String content;
		private int marksScored;
		private int totalMarks;
		private Timestamp timestamp;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		private Topic topics;
		
		
		@OneToMany(mappedBy="quiz")
		@JsonIgnore
		private List<Questions> questions;

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




		public int getMarksScored() {
			return marksScored;
		}




		public void setMarksScored(int marksScored) {
			this.marksScored = marksScored;
		}




		public int getTotalMarks() {
			return totalMarks;
		}




		public void setTotalMarks(int totalMarks) {
			this.totalMarks = totalMarks;
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




		public List<Questions> getQuestions() {
			return questions;
		}




		public void setQuestions(List<Questions> questions) {
			this.questions = questions;
		}




		public Quiz(int id, String topic, String content, int marksScored, int totalMarks, Timestamp timestamp,
				Topic topics, List<Questions> questions) {
			super();
			this.id = id;
			this.topic = topic;
			this.content = content;
			this.marksScored = marksScored;
			this.totalMarks = totalMarks;
			this.timestamp = timestamp;
			this.topics = topics;
			this.questions = questions;
		}




		@Override
		public String toString() {
			return "Quiz [id=" + id + ", topic=" + topic + ", content=" + content + ", marksScored=" + marksScored
					+ ", totalMarks=" + totalMarks + ", timestamp=" + timestamp + ", topics=" + topics + ", questions="
					+ questions + "]";
		}




		public Quiz() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}