package com.example.demo.entity;

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
@Table(name="questions")
public class Questions {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@Lob
		@Column(columnDefinition = "LONGTEXT")
		private String question;
		@Lob
		@Column(columnDefinition = "LONGTEXT")
		private String option1;
		@Lob
		@Column(columnDefinition = "LONGTEXT")
		private String option2;
		@Lob
		@Column(columnDefinition = "LONGTEXT")
		private String option3;
		@Lob
		@Column(columnDefinition = "LONGTEXT")
		private String option4;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		private Quiz quiz;

	
		


		
		
}