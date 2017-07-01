package com.sunnypatel.daysofrefactoringjava.day30.returnasap.solution;

public class Product {
	private String name;
	private double price;
	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	
}
