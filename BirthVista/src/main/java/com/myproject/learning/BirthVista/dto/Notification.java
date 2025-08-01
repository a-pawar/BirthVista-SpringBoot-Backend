package com.myproject.learning.BirthVista.dto;

import java.time.LocalDate;

public class Notification {

	private String content;
	private LocalDate timeStamp;
	
	public Notification() {
	}
	
	public Notification(String content, LocalDate timeStamp) {
		super();
		this.content = content;
		this.timeStamp = timeStamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	
}
