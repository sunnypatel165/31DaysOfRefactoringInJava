package com.sunnypatel.daysofrefactoringjava.day23.introduceparameterobject.solution;

import java.util.List;

public class RegistrationContext {
	private double amount;
	private Student student;
	private List<Course> courses;
	private double credits;

	public RegistrationContext(double amount, Student student, List<Course> courses, double credits) {
		this.amount = amount;
		this.student = student;
		this.courses = courses;
		this.credits = credits;
	}

	public double getAmount() {
		return amount;
	}

	public Student getStudent() {
		return student;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public double getCredits() {
		return credits;
	}

}
