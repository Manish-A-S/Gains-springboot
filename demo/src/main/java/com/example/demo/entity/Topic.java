package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.Arrays;
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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="topic")
public class Topic {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String topic;
		@Lob
		@Column(columnDefinition = "LONGTEXT")
		private String content;
		private List<Boolean> action;
		public List<Boolean> getAction() {
			return action;
		}

		public void setAction(List<Boolean> action) {
			this.action = action;
		}

		private Timestamp timestamp;
		

		public Timestamp getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
		}

		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		private User user;
		
		@OneToMany(mappedBy="topics",cascade=CascadeType.ALL, orphanRemoval= true)
		@JsonIgnore
		private List<Doubts> doubts;
		
		@OneToMany(mappedBy="topics",cascade=CascadeType.ALL, orphanRemoval= true)
		@JsonIgnore
		private List<Notes> notes;
		
		@OneToMany(mappedBy="topics",cascade=CascadeType.ALL, orphanRemoval= true)
		@JsonIgnore
		private List<Quiz> quiz;
		
		@OneToMany(mappedBy="topics",cascade=CascadeType.ALL, orphanRemoval= true)
		@JsonIgnore
		private List<Tutor> tutor;

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

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public List<Doubts> getDoubts() {
			return doubts;
		}

		public void setDoubts(List<Doubts> doubts) {
			this.doubts = doubts;
		}

		public List<Notes> getNotes() {
			return notes;
		}

		public void setNotes(List<Notes> notes) {
			this.notes = notes;
		}

		public List<Quiz> getQuiz() {
			return quiz;
		}

		public void setQuiz(List<Quiz> quiz) {
			this.quiz = quiz;
		}

		public List<Tutor> getTutor() {
			return tutor;
		}

		public void setTutor(List<Tutor> tutor) {
			this.tutor = tutor;
		}

		public Topic(int id, String topic, String content, List<Boolean> action, Timestamp timestamp, User user,
				List<Doubts> doubts, List<Notes> notes, List<Quiz> quiz, List<Tutor> tutor) {
			super();
			this.id = id;
			this.topic = topic;
			this.content = content;
			this.action = action;
			this.timestamp = timestamp;
			this.user = user;
			this.doubts = doubts;
			this.notes = notes;
			this.quiz = quiz;
			this.tutor = tutor;
		}

		@Override
		public String toString() {
			return "Topic [id=" + id + ", topic=" + topic + ", content=" + content + ", action=" + action
					+ ", timestamp=" + timestamp + ", user=" + user + ", doubts=" + doubts + ", notes=" + notes
					+ ", quiz=" + quiz + ", tutor=" + tutor + "]";
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Topic() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		
}