package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="otp")
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private int otp;
	private LocalDateTime time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	public Otp(int id, String email, int otp, LocalDateTime time) {
		super();
		this.id = id;
		this.email = email;
		this.otp = otp;
		this.time = time;
	}
	@Override
	public String toString() {
		return "Otp [id=" + id + ", email=" + email + ", otp=" + otp + ", time=" + time + "]";
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime localDateTime) {
		this.time = localDateTime;
	}
	public Otp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
