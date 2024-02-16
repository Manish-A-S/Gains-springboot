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
@Table(name="Notes")
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String topic;
	private String content;
	private String summary;
	private Timestamp timestamp;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Topic topics;


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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
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


	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Notes(int id, String topic, String content, String summary, Timestamp timestamp, Topic topics) {
		super();
		Id = id;
		this.topic = topic;
		this.content = content;
		this.summary = summary;
		this.timestamp = timestamp;
		this.topics = topics;
	}
	
	
}
