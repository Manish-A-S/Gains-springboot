package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Topic")
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String topic;
	
	@OneToMany(mappedBy="topics")
	@JsonIgnore
	private List<Notes> notes;
	
	@OneToMany(mappedBy="topics")
	@JsonIgnore
	private List<Doubts> doubts;
	
	@OneToMany(mappedBy="topics")
	@JsonIgnore
	private List<Quiz> quiz;
	
	@OneToMany(mappedBy="topics")
	@JsonIgnore
	private List<Tutor> tutor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<Notes> getNotes() {
		return notes;
	}

	public void setNotes(List<Notes> notes) {
		this.notes = notes;
	}

	public List<Doubts> getDoubts() {
		return doubts;
	}

	public void setDoubts(List<Doubts> doubts) {
		this.doubts = doubts;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topic(int id, String topic, List<Notes> notes, List<Doubts> doubts, List<Quiz> quiz, List<Tutor> tutor,
			User user) {
		super();
		Id = id;
		this.topic = topic;
		this.notes = notes;
		this.doubts = doubts;
		this.quiz = quiz;
		this.tutor = tutor;
		this.user = user;
	}

	
}
