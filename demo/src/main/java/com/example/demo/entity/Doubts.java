package com.example.demo.entity;



import java.sql.Timestamp;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Doubts")
public class Doubts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String topic;
	private String content;
	private String quesstion;
	private String answer;
	private Timestamp timestamp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Topic topics;

	

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

	public String getQuesstion() {
		return quesstion;
	}

	public void setQuesstion(String quesstion) {
		this.quesstion = quesstion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Doubts(int id, String topic, String content, String quesstion, String answer, Timestamp timestamp,
			Topic topics) {
		super();
		this.id = id;
		this.topic = topic;
		this.content = content;
		this.quesstion = quesstion;
		this.answer = answer;
		this.timestamp = timestamp;
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "Doubts [id=" + id + ", topic=" + topic + ", content=" + content + ", quesstion=" + quesstion
				+ ", answer=" + answer + ", timestamp=" + timestamp + ", topics=" + topics + "]";
	}

	


}
