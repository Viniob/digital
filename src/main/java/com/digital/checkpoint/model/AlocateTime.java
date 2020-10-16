package com.digital.checkpoint.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AlocateTime {

	@Id @GeneratedValue
	private Integer id;
	private String registration;
	private Integer projectCode;
	private LocalDateTime dayWorked;
	private Integer workedTime;

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

	public Integer getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(Integer projectCode) {
		this.projectCode = projectCode;
	}

	public LocalDateTime getDayWorked() {
		return dayWorked;
	}

	public void setDayWorked(LocalDateTime dayWorked) {
		this.dayWorked = dayWorked;
	}

	public Integer getWorkedTime() {
		return workedTime;
	}

	public void setWorkedTime(Integer workedTime) {
		this.workedTime = workedTime;
	}

	@Override
	public String toString() {
		return "AlocateTime [id=" + id + ", registration=" + registration + ", projectCode=" + projectCode
				+ ", dayWorked=" + dayWorked + ", workedTime=" + workedTime + "]";
	}

	

}
