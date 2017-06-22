package com.sunnypatel.daysofrefactoringjava.day23.introduceparameterobject.solution;

public class Student {
	private String name;
	private int age;
	private long phone;

	public Student(String name, int age, long phone) {
		this.name = name;
		this.age = age;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public long getPhone() {
		return phone;
	}
}
