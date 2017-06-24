package com.sunnypatel.daysofrefactoringjava.day25.introducedesignbycontractchecks.solution;

import java.util.List;

public class CashRegister {

	private double orderTotal = 0;

	public double calculateOrderTotal(List<Product> products, Customer customer) {
		if (customer == null || products == null || products.size() == 0)
			throw new IllegalArgumentException("One of the arguments passed is null!");

		orderTotal = 0;
		products.forEach(product -> orderTotal += product.getPrice());

		if (orderTotal == 0)
			throw new RuntimeException("Not a correct order, please check!");

		customer.addBalance(orderTotal); // impure!!!
		return orderTotal;

	}

}
