package com.sunnypatel.daysofrefactoringjava.day26.removedoublenegative.solution;

public class Customer {
	private String name;
	private double balance;

	public Customer(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	public boolean isFlagged(){
		return balance <= 30.0; //magic number!
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

}
