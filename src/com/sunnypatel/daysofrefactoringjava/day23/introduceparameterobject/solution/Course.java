package com.sunnypatel.daysofrefactoringjava.day23.introduceparameterobject.solution;

public class Course {
	private String courseName;
	private String coruseId;
	private String professorName;

	public Course(String courseName, String coruseId, String professorName) {
		this.courseName = courseName;
		this.coruseId = coruseId;
		this.professorName = professorName;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCoruseId() {
		return coruseId;
	}

	public String getProfessorName() {
		return professorName;
	}
}
