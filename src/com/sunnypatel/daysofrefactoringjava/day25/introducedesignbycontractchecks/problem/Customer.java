package com.sunnypatel.daysofrefactoringjava.day25.introducedesignbycontractchecks.problem;

public class Customer {
	private String name;
	private double balance;

	public void addBalance(double amount) {
		balance += amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

}
