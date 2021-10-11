package com.demo.response;

import org.springframework.http.HttpStatus;

import com.demo.model.Student;

public class ResponseModel {

	private Student student;
	private String status;
	private HttpStatus httpStatus;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
