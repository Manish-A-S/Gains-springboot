package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="quiz")
public class Quiz {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String topic;
		private int marksScored;
		private int totalMarks;
		private List<Integer> correctAnswers;
		private List<Integer> selectedAnswers;
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









		public List<Integer> getCorrectAnswers() {
			return correctAnswers;
		}




		public void setCorrectAnswers(List<Integer> correctAnswers) {
			this.correctAnswers = correctAnswers;
		}




		public List<Integer> getSelectedAnswers() {
			return selectedAnswers;
		}




		public void setSelectedAnswers(List<Integer> selectedAnswers) {
			this.selectedAnswers = selectedAnswers;
		}




		@Override
		public String toString() {
			return "Quiz [id=" + id + ", topic=" + topic + ", marksScored=" + marksScored + ", totalMarks=" + totalMarks
					+ ", correctAnswers=" + correctAnswers + ", selectedAnswers=" + selectedAnswers + ", timestamp="
					+ timestamp + ", topics=" + topics + ", questions=" + questions + "]";
		}




		public Quiz(int id, String topic, int marksScored, int totalMarks, List<Integer> correctAnswers,
				List<Integer> selectedAnswers, Timestamp timestamp, Topic topics, List<Questions> questions) {
			super();
			this.id = id;
			this.topic = topic;
			this.marksScored = marksScored;
			this.totalMarks = totalMarks;
			this.correctAnswers = correctAnswers;
			this.selectedAnswers = selectedAnswers;
			this.timestamp = timestamp;
			this.topics = topics;
			this.questions = questions;
		}




		public Quiz() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}