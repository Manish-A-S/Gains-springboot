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


@Entity
@Table(name="notes")
public class Notes {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String topic;
		@Lob
		@Column(columnDefinition = "LONGTEXT")
		private String content;
		private List<String> summary;
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


		public List<String> getSummary() {
			return summary;
		}


		public void setSummary(List<String> summary) {
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


		@Override
		public String toString() {
			return "Notes [id=" + id + ", topic=" + topic + ", content=" + content + ", summary=" + summary
					+ ", timestamp=" + timestamp + ", topics=" + topics + "]";
		}


		public Notes(int id, String topic, String content, List<String> summary, Timestamp timestamp, Topic topics) {
			super();
			this.id = id;
			this.topic = topic;
			this.content = content;
			this.summary = summary;
			this.timestamp = timestamp;
			this.topics = topics;
		}


		public Notes() {
			super();
			// TODO Auto-generated constructor stub
		}
		


		
}