package com.digital.checkpoint.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CheckPoint {

	@Id @GeneratedValue
	private Integer id;
	private String registration;
	private LocalDateTime day;
	private LocalDateTime ArrivalTime;
	private LocalDateTime ExitTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public LocalDateTime getDay() {
		return day;
	}

	public void setDay(LocalDateTime day) {
		this.day = day;
	}

	public LocalDateTime getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public LocalDateTime getExitTime() {
		return ExitTime;
	}

	public void setExitTime(LocalDateTime exitTime) {
		ExitTime = exitTime;
	}

	@Override
	public String toString() {
		return "CheckPoint [id=" + id + ", registration=" + registration + ", day=" + day + ", ArrivalTime="
				+ ArrivalTime + ", ExitTime=" + ExitTime + "]";
	}

	

}
